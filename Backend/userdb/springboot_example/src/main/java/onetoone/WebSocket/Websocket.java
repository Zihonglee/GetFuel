package onetoone.WebSocket;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

import onetoone.Users.User;
import onetoone.Users.UserRepository;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@ServerEndpoint("/websocket/{userid}")
@Component
public class Websocket {

    private final Logger logger1 = LoggerFactory.getLogger(Websocket.class);
    private static UserRepository userRepository;
    private LocalDateTime timeCreated = LocalDateTime.now();

    @Autowired
    public void setUserRepository(UserRepository repo){
        userRepository = repo
    }

    private static Map<Session,User> sessionIdMap = new Hashtable<>();
    private static Map<User,Session> idSessionMap = new Hashtable<>();

    private final Logger logger = logger1;

    private void broadcast(String message) {
        sessionIdMap.forEach((session, user) -> {
            try {
                session.getBasicRemote().sendText(message);
            }
            catch (IOException e) {
                logger.info("Exception: " + e.getMessage().toString());
                e.printStackTrace();
            }

        });

    }


    @OnOpen
    public void onOpen(Session session,@PathParam("userid") Long userid ) {
        try {

logger.info("User connected");
User user = userRepository.getUserById(userid);

            sessionIdMap.put(session,user);
            idSessionMap.put(user,session);

            String message = "User:" +user.getName()+"open the application";
            broadcast(message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(Session session, String UserInfo){
logger.info("Enter into Message: Got Message:"+ UserInfo);
User user = sessionIdMap.get(session);
Scanner sc = new Scanner(UserInfo);
String[] list = sc.delimiter().split(",");
boolean userexist = false;

for(int i = 0; i< userRepository.findAll().size(); i++){
    if(userRepository.findAll().get(i).getId().equals(list[0])){
        userexist= true;
    }
}
if(userexist) {
    Long id = Long.parseLong(list[0]);
    User edituser = userRepository.getUserById(id);
    edituser.setName(list[1]);
    edituser.setEmail(list[2]);
    edituser.setPassword(list[3]);
    edituser.setRoleType(list[4]);
    userRepository.save(edituser);
}else {
    User newuser = new User(list[1],list[2],list[3],list[4]);
    userRepository.save(newuser);
}

    }

    @OnClose
    public void onClose(Session session){
        logger.info("Entered into Close");

        User user = sessionIdMap.get(session);
        sessionIdMap.remove(session);
        idSessionMap.remove(user);

        // broadcase that the user disconnected
        String message = user + " disconnected";
        broadcast(message);

    }

    @OnError
    public void onError(Session session,Throwable throwable){
        logger.info("Entered into Error");
        throwable.printStackTrace();
    }



}
