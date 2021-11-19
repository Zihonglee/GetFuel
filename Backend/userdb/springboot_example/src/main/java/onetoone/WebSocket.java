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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerEndpoint(value = "/websocket/{restaurant}")
@Component
public class WebSocket
{
	private final Logger logger = LoggerFactory.getLogger(WebSocket.class);
	private static Map < Session, User > sessionUserMap = new Hashtable < > ();
	private static Map < User, Session > userSessionMap = new Hashtable < > ();

	@OnOpen
	public void onOpen(Session session, @RequestBody User user) throws IOException
	{
		logger.info("Client connected");

		sessionUserMap.put(session, user);
		userSessionMap.put(user, session);

		String message = "User:" + user.getName() + " has Joined the application";
		broadcast(message);	
	}
//here
	private void broadcast(String message) 
	{
		sessionUsernameMap.forEach((session, username) -> 
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
	public void onMessage(Session session, Restaurant restaurant) throws IOException
	{
		logger.info("Restaurant added");
		sendMessage(session, "Server Received: " + restaurant.get);
	}

	/**
	 * This method is called when the connection between the client and server is
	 * closed. It is also called after onError if client is disconnected due to the
	 * error. You should use this method to clear any data and update any values you
	 * need to before the client disconnects.
	 *
	 * ** Be careful about trying to send things to the client here as the client
	 * has probably already disconnected and is not receiving anything. **
	 * 
	 * @param session the representation of the client
	 */
	@OnClose
	public void onClose(Session session)
	{
		// No need to handle anything here
		logger.info("Client disconnected");
	}

	/**
	 * This method is called when an error has occurred between the client and the
	 * server. If the error is fatal, the connection will also close.
	 * 
	 * @param session   the representation of the client
	 * @param throwable The exception that was thrown
	 */

	@OnError
	public void onError(Session session, Throwable throwable) 
	{
		// If the app crashes/closes unexpectedly then the websocket will not get closed properly and will throw an error
		// We are ignoring that exception in this situation because it clutters the console
		if (!throwable.getClass().getSimpleName().equals("EOFException")) 
		{
			logger.debug("An error has occurred");
			throwable.printStackTrace();
		}
	}

	/**
	 * Helper method to send a string to the given client
	 * 
	 * @param session The representation of the client
	 * @param message The message to be sent
	 */
	private void sendMessage(Session session, String message) 
	{
		try {
			session.getBasicRemote().sendText(message);
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

}

