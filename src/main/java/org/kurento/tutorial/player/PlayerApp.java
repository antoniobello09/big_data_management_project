package org.kurento.tutorial.player;

import org.kurento.client.KurentoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

/**
 * Play of a video through WebRTC (main).
 *
 * @author Boni Garcia (bgarcia@gsyc.es)
 * @since 6.1.1
 */
@EnableWebSocket
@SpringBootApplication
public class PlayerApp implements WebSocketConfigurer {

  @Bean
  public PlayerHandler handler() {
    return new PlayerHandler();
  }

  @Bean
  public KurentoClient kurentoClient() {
    return KurentoClient.create();
  }

  @Bean
  public ServletServerContainerFactoryBean createServletServerContainerFactoryBean() {
    ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
    container.setMaxTextMessageBufferSize(32768);
    return container;
  }

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(handler(), "/player");
  }

  public static void main(String[] args) throws Exception {
    SpringApplication.run(PlayerApp.class, args);
  }
}
