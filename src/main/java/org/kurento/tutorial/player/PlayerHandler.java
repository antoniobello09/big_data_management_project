
package org.kurento.tutorial.player;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.kurento.client.EndOfStreamEvent;
import org.kurento.client.ErrorEvent;
import org.kurento.client.EventListener;
import org.kurento.client.KurentoClient;
import org.kurento.client.MediaPipeline;
import org.kurento.client.PlayerEndpoint;
import org.kurento.module.platedetector.PlateDetectedEvent;
import org.kurento.module.platedetector.PlateDetectorFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;




public class PlayerHandler extends TextWebSocketHandler {

  @Autowired
  private KurentoClient kurento;

  private final Gson gson = new GsonBuilder().create();
  private final ConcurrentHashMap<String, UserSession> users = new ConcurrentHashMap<>();

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    JsonObject jsonMessage = gson.fromJson(message.getPayload(), JsonObject.class);
    String sessionId = session.getId();

    try {
      switch (jsonMessage.get("id").getAsString()) {
        case "start":
          start(session, jsonMessage);
          break;
        case "stop":
          stop(sessionId);
          break;
        case "pause":
          pause(sessionId);
          break;
        case "resume":
          resume(session);
          break;
        default:
          sendError(session, "Invalid message with id " + jsonMessage.get("id").getAsString());
          break;
      }
    } catch (Throwable t) {
      sendError(session, t.getMessage());
    }
  }

  private void start(final WebSocketSession session, JsonObject jsonMessage) {
    // 1. Media pipeline
    final UserSession user = new UserSession();
    MediaPipeline pipeline = kurento.createMediaPipeline();
    user.setMediaPipeline(pipeline);
    String videourl = jsonMessage.get("videourl").getAsString();
    final PlayerEndpoint playerEndpoint = new PlayerEndpoint.Builder(pipeline, videourl).build();
    user.setPlayerEndpoint(playerEndpoint);
    users.put(session.getId(), user);


     PlateDetectorFilter plateDetectorFilter = new PlateDetectorFilter.Builder(pipeline).build();

    playerEndpoint.connect(plateDetectorFilter);
    //plateDetectorFilter.connect(webRtcEndpoint);

    plateDetectorFilter.addPlateDetectedListener(new EventListener<PlateDetectedEvent>() {
      @Override
      public void onEvent(PlateDetectedEvent event) {
        JsonObject response = new JsonObject();
        response.addProperty("id", "plateDetected");
        response.addProperty("plate", event.getPlate());
        try {
          session.sendMessage(new TextMessage(response.toString()));
        } catch (Throwable t) {
          sendError(session, t.getMessage());
        }
      }
    });

    // 3. PlayEndpoint
    playerEndpoint.addErrorListener(new EventListener<ErrorEvent>() {
      @Override
      public void onEvent(ErrorEvent event) {
        sendPlayEnd(session);
      }
    });

    playerEndpoint.addEndOfStreamListener(new EventListener<EndOfStreamEvent>() {
      @Override
      public void onEvent(EndOfStreamEvent event) {
        sendPlayEnd(session);
      }
    });

    playerEndpoint.play();
  }

  private void pause(String sessionId) {
    UserSession user = users.get(sessionId);

    if (user != null) {
      user.getPlayerEndpoint().pause();
    }
  }

  private void resume(final WebSocketSession session) {
    UserSession user = users.get(session.getId());

    if (user != null) {
      user.getPlayerEndpoint().play();
    }
  }

  private void stop(String sessionId) {
    UserSession user = users.remove(sessionId);

    if (user != null) {
      user.release();
    }
  }

  public void sendPlayEnd(WebSocketSession session) {
    if (users.containsKey(session.getId())) {
      JsonObject response = new JsonObject();
      response.addProperty("id", "playEnd");
      sendMessage(session, response.toString());
    }
  }

  private void sendError(WebSocketSession session, String message) {
    if (users.containsKey(session.getId())) {
      JsonObject response = new JsonObject();
      response.addProperty("id", "error");
      response.addProperty("message", message);
      sendMessage(session, response.toString());
    }
  }

  private synchronized void sendMessage(WebSocketSession session, String message) {
    try {
      session.sendMessage(new TextMessage(message));
    } catch (IOException e) {
    }
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    stop(session.getId());
  }
}
