package onetoone.WebSocket;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
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
import org.springframework.stereotype.Component;

@ServerEndpoint("/websocket/{user}")
@Component
public class Websocket {

    private final Logger logger1 = LoggerFactory.getLogger(Websocket.class);
    UserRepository userRepository;


    private static Map<Session,Long> sessionIdMap = new Hashtable<>();
    private static Map<Long,Session> idSessionMap = new Hashtable<>();

    private final Logger logger = logger1;


    @OnOpen
    public void onOpen(Session session,@PathParam("username") String username ) {
        try {

logger.info("User connected");
User user = userRepository.findByName(username);

            sessionIdMap.put(session,user.getId());
            idSessionMap.put(user.getId(),session);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(Session session){
//update all data ?
    }

    @OnClose
    public void onClose(Session session){

    }

    @OnError
    public void onError(Session session,Throwable throwable){
        logger.info("Entered into Error");
        throwable.printStackTrace();
    }



}
