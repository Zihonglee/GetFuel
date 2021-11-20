package onetoone;

import javax.websocket.server.ServerEndpointConfig.Configurator;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
@EnableWebSocket
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "dev")
public class WebSocketConfig extends Configurator
{
	@Bean
	public ServerEndpointExporter serverEndpointExporter() 
	{
		return new ServerEndpointExporter();
	}
}
