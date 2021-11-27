package onetoone.WebSocket;
import java.io.IOException;
import java.util.Hashtable;
//import java.util.List;
import java.util.Map;

import javax.websocket.*;
import javax.websocket.server.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import onetoone.Cuisine.Cuisine;
import onetoone.Cuisine.CuisineRepository;
import onetoone.Restaurants.Restaurant;
//import onetoone.Restaurants.RestaurantController;
import onetoone.Restaurants.RestaurantRepository;
import onetoone.Users.User;
import onetoone.Users.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@ServerEndpoint(value = "/websocketRes/{userId}")
public class WebSocketRest
{
    private static RestaurantRepository restRepo;
    private static UserRepository userRepo;
    private static CuisineRepository cuisineRepo;

    private static Map<Session, User> sessionUserMap = new Hashtable<>();
    private static Map<User, Session> userSessionMap = new Hashtable<>();

    private final Logger logger = LoggerFactory.getLogger(WebSocketRest.class);

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

        String message = user.getRoleType() + ": " + user.getName() + " has Joined the application";
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
        User user = sessionUserMap.get(session);
        if (user.getRoleType().equals("admin") || user.getRoleType().equals("maintainer"))
        {
            String[] list = RestaurantInfo.split(",");
            if (list[0] == null || list[1] == null || list[2] == null || list[3] == null || list[4] == null)
            {
                broadcast("The restaurant has not added: Insufficient information");
            }
            else
            {
                Cuisine cs = null;
                String check = list[3];
                boolean checkIfCusineExist = false;
                for (int i = 0; i < cuisineRepo.findAll().size(); ++i)
                {
                    if (cuisineRepo.findAll().get(i).getCuisineType().equals(check))
                    {
                        cs = cuisineRepo.findAll().get(i);
                        checkIfCusineExist = true;
                        break;
                    }
                }
                logger.info("Entered into Message and got the message:" + RestaurantInfo);
                if (!checkIfCusineExist)
                {
                    broadcast("The restaurant has not added: Cuisine type does not exist");
                }
                else
                {
                    Restaurant rest = new Restaurant(list[0], list[1], list[2], cs, list[4]);
                    restRepo.save(rest);
                    cs.getRestaurants().add(rest);
                    cuisineRepo.save(cs);
                    broadcast("New Restaurant Info \nName: " + list[0] + "\nPrice: " + list[1] + "\nRating: " + list[2] + "\nCuisine Type: " + list[3] + "\nAdded by: " + user.getName());
                }
//				scan.close();
            }
        }
        else
        {
            broadcast("Access Denied: Cannot add restaurant");
        }
    }

    @OnClose
    public void onClose(Session session)
    {
        logger.info("Client disconnected");

        User username = sessionUserMap.get(session);
        sessionUserMap.remove(session);
        userSessionMap.remove(username);

        String message = username.getName() + " disconnected";
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
