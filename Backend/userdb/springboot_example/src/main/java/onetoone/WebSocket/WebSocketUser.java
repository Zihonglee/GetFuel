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

@ServerEndpoint("/websocketUser/{userid}")
@Component
public class WebSocketUser {

    private final Logger logger1 = LoggerFactory.getLogger(WebSocketUser.class);
    private static UserRepository userRepository;
    private LocalDateTime timeCreated = LocalDateTime.now();

    @Autowired
    public void setUserRepository(UserRepository repo){
        userRepository = repo;
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
    public void onOpen(Session session,@PathParam("userid") Long userid )throws IOException {


logger.info("User connected");
User user = userRepository.getUserById(userid);

            sessionIdMap.put(session,user);
            idSessionMap.put(user,session);

            String message = "User:" +user.getName()+"open the application";
            broadcast(message);


    }

    @OnMessage
    public void onMessage(Session session, String UserInfo){
logger.info("Enter into Message: Got Message:"+ UserInfo);
User user = sessionIdMap.get(session);
Scanner sc = new Scanner(UserInfo);
String[] list = sc.delimiter().split(",");
boolean userexist = false;

boolean userIsAdmin = false;

if(user.getRoleType().equals("admin")){
    userIsAdmin = true;
}else
{
    logger.info("User is not a admin");
    return ;
}

for(int i = 0; i< userRepository.findAll().size(); i++){
    if(userRepository.findAll().get(i).getId().equals(list[0])){
        userexist= true;
    }
}
sc.close();
if(userexist && userIsAdmin) {
    Long id = Long.parseLong(list[0]);
    User edituser = userRepository.getUserById(id);
    edituser.setName(list[1]);
    edituser.setEmail(list[2]);
    edituser.setPassword(list[3]);
    edituser.setRoleType(list[4]);
    userRepository.save(edituser);
    logger.info("User Info : \n Name:"+ list[1]+"\nEmail:"+list[2]+"\nPassword"+list[3]+"\nRoletype"+list[4]+"Changed By User:"+user.getName());

}else {
    User newuser = new User(list[1],list[2],list[3],list[4]);
    userRepository.save(newuser);
    logger.info("User Info : \n Name:"+ list[1]+"\nEmail:"+list[2]+"\nPassword"+list[3]+"\nRoletype"+list[4]+"Added By User:"+user.getName());

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
