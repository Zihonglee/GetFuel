package onetoone;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.websocket.*;
import javax.websocket.server.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import onetoone.Restaurants.Restaurant;
import onetoone.Users.User;
import onetoone.Users.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerEndpoint(value = "/websocket/{userId}", configurator = WebSocketConfig.class)
@Controller
@ConditionalOnClass(value = WebSocketConfig.class)
public class WebSocket
{
	private static UserRepository repo;
	private final Logger logger = LoggerFactory.getLogger(WebSocket.class);
	private static Map < Session, User > sessionUserMap = new Hashtable < > ();
	private static Map < User, Session > userSessionMap = new Hashtable < > ();

	@Autowired
	public void setUserRepository(UserRepository repo) 
	{
		WebSocket.repo = repo;
	}

	@OnOpen
	public void onOpen(Session session, @PathParam(value = "userId") int userId) throws IOException
	{
		Long integers = Long.valueOf(userId);
		User user = repo.getUserById(integers);
		logger.info("Client connected");

		sessionUserMap.put(session, user);
		userSessionMap.put(user, session);

		String message = "User:" + user.getName() + " has Joined the application";
		broadcast(message);	
	}
	
	private void broadcast(String message) 
	{
		sessionUserMap.forEach((session, user) -> 
		{
			try 
			{
				session.getBasicRemote().sendText(message);
			} catch (IOException e)
			{
				logger.info("Exception: " + e.getMessage().toString());
				e.printStackTrace();
			}

		});
	}
	@OnMessage
	public void onMessage(@RequestBody Restaurant restaurant) throws IOException
	{
		logger.info("Restaurant added");
		broadcast("New Restaurant: " + restaurant.getName());
	}

	@OnClose
	public void onClose(Session session)
	{
		logger.info("Client disconnected");

	    User username = sessionUserMap.get(session);
	    sessionUserMap.remove(session);
	    userSessionMap.remove(username);

	    String message = username + " disconnected";
	    broadcast(message);
	}
	
	@OnError
	public void onError(Session session, Throwable throwable) 
	{
		if (!throwable.getClass().getSimpleName().equals("EOFException")) 
		{
			logger.debug("An error has occurred");
			throwable.printStackTrace();
		}
	}
}

