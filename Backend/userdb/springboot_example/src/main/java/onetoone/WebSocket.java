package onetoone;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import onetoone.Restaurants.Restaurant;
import onetoone.Users.User;
import onetoone.Users.UserController;
import onetoone.Users.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerEndpoint(value = "/websocket/{userId}")
@Component
public class WebSocket
{
	UserController usercontroller;
	private final Logger logger = LoggerFactory.getLogger(WebSocket.class);
	private static Map < Session, User > sessionUserMap = new Hashtable < > ();
	private static Map < User, Session > userSessionMap = new Hashtable < > ();

	@OnOpen
	public void onOpen(Session session, @PathParam("userId") int userId) throws IOException
	{
		Long integers = Long.valueOf(userId);
		User user = usercontroller.getPersonById(integers);
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

