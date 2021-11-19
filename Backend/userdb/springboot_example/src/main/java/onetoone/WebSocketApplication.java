package onetoone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableJpaRepositories
@EnableWebSocket
public class WebSocketApplication 
{
    public static void main(String[] args)
    {
        SpringApplication.run(WebSocketApplication.class, args);
    }
}
