package onetoone;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.websocket.*;
import javax.websocket.server.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import onetoone.Cuisine.CuisineRepository;
import onetoone.Restaurants.Restaurant;
import onetoone.Restaurants.RestaurantController;
import onetoone.Restaurants.RestaurantRepository;
import onetoone.Users.User;
import onetoone.Users.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@ServerEndpoint(value = "/websocket/{userId}")
public class WebSocket 
{
	private static RestaurantRepository restRepo; 
	private static UserRepository userRepo; 
	private static CuisineRepository cuisineRepo;
	private static RestaurantController restController;

	private static Map<Session, User> sessionUserMap = new Hashtable<>();
	private static Map<User, Session> userSessionMap = new Hashtable<>();

	private final Logger logger = LoggerFactory.getLogger(WebSocket.class);

	@Autowired
	public void setRestaurantRepository(RestaurantController RestController) 
	{
		restController = RestController;
	}

	@Autowired
	public void setRestaurantRepository(RestaurantRepository repo) 
	{
		restRepo = repo;
	}

	@Autowired
	public void setUserRepository(UserRepository repo) 
	{
		userRepo = repo;
	}


	@Autowired
	public void setCuisineRepository(CuisineRepository repo) 
	{
		cuisineRepo = repo;
	}
	@OnOpen
	public void onOpen(Session session, @PathParam("userId") Long userId) throws IOException
	{
		logger.info("Entered into Open");

		User user = userRepo.getUserById(userId);
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
	public void onMessage(Session session, String RestaurantInfo) throws IOException 
	{
		String[] list = new String[5];
		Scanner scan = new Scanner(RestaurantInfo);
		String wholeString = scan.next();
		String store = "";
		int number = 0;
		for (int i = 0; i < wholeString.length(); ++i)
		{
			if (wholeString.charAt(i) == ',')
			{
				list[number] = store;
				++number;
				store = "";
			}
			else
			{
				store += wholeString.charAt(i);
			}
		}
		list[number] = store;
		store = null;		
		Long Id = null;
		String check = list[3];
		String message = String.valueOf(cuisineRepo.findAll().size());
		broadcast(message);
		for (int i = 0; i < cuisineRepo.findAll().size(); ++i)
		{
			if (cuisineRepo.findAll().get(i).getCuisineType().equals(check) || cuisineRepo.findAll().get(i).getCuisineType() != null)
			{
				Id = cuisineRepo.findAll().get(i).getId();
				System.out.println(cuisineRepo.getCuisineById(Id).getCuisineType());
				break;
			}
		}
		scan.close();
		restController.addRestaurant(new Restaurant(list[0], list[1], list[2], cuisineRepo.getCuisineById(Id), list[4]));
		logger.info("Entered into Message: Got Message:" + RestaurantInfo);
		User username = sessionUserMap.get(session);
		broadcast("New Restaurant Info \nName: " + list[0] + "\nPrice: " + list[1] + "\nRating: " + list[2] + "\nCuisine Type: " + list[3] + "\nAdded by: " + username.getName());
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

	@SuppressWarnings("unused")// not necessary 
	private String getRestaurantAddedHistory() 
	{
		List<Restaurant> userList = restRepo.findAll();

		StringBuilder sb = new StringBuilder();
		if(userList != null && userList.size() != 0) 
		{
			for (int i = 0; i < userList.size(); ++i)
			{
				sb.append(i + ") " + userList.get(i).getName() + "\n");
			}
		}
		return sb.toString();
	}
}