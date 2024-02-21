package org.kurento.tutorial.player;

import org.kurento.client.MediaPipeline;
import org.kurento.client.PlayerEndpoint;

public class UserSession {

  private MediaPipeline mediaPipeline;
  private PlayerEndpoint playerEndpoint;

  public UserSession() {
  }


  public MediaPipeline getMediaPipeline() {
    return mediaPipeline;
  }

  public void setMediaPipeline(MediaPipeline mediaPipeline) {
    this.mediaPipeline = mediaPipeline;
  }

  public PlayerEndpoint getPlayerEndpoint() {
    return playerEndpoint;
  }

  public void setPlayerEndpoint(PlayerEndpoint playerEndpoint) {
    this.playerEndpoint = playerEndpoint;
  }

  public void release() {
    this.playerEndpoint.stop();
    this.mediaPipeline.release();
  }
}
