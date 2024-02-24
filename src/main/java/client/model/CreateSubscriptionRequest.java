/*
 * FIWARE-NGSI v2 Specification
 * TODO: Add a description
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * CreateSubscriptionRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2024-02-23T12:12:13.839+01:00")
public class CreateSubscriptionRequest {
  @SerializedName("description")
  private String description = null;

  @SerializedName("subject")
  private Object subject = null;

  @SerializedName("notification")
  private Object notification = null;

  @SerializedName("expires")
  private String expires = null;

  @SerializedName("throttling")
  private Integer throttling = null;

  public CreateSubscriptionRequest description(String description) {
    this.description = description;
    return this;
  }

   /**
   * 
   * @return description
  **/
  @ApiModelProperty(example = "One subscription to rule them all", required = true, value = "")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public CreateSubscriptionRequest subject(Object subject) {
    this.subject = subject;
    return this;
  }

   /**
   * 
   * @return subject
  **/
  @ApiModelProperty(example = "{\"entities\":[{\"idPattern\":\".*\",\"type\":\"Room\"}],\"condition\":{\"attrs\":[\"temperature\"],\"expression\":{\"q\":\"temperature>40\"}}}", required = true, value = "")
  public Object getSubject() {
    return subject;
  }

  public void setSubject(Object subject) {
    this.subject = subject;
  }

  public CreateSubscriptionRequest notification(Object notification) {
    this.notification = notification;
    return this;
  }

   /**
   * 
   * @return notification
  **/
  @ApiModelProperty(example = "{\"http\":{\"url\":\"http://localhost:1234\"},\"attrs\":[\"temperature\",\"humidity\"]}", required = true, value = "")
  public Object getNotification() {
    return notification;
  }

  public void setNotification(Object notification) {
    this.notification = notification;
  }

  public CreateSubscriptionRequest expires(String expires) {
    this.expires = expires;
    return this;
  }

   /**
   * 
   * @return expires
  **/
  @ApiModelProperty(example = "4/5/2016 2:00:00 PM", required = true, value = "")
  public String getExpires() {
    return expires;
  }

  public void setExpires(String expires) {
    this.expires = expires;
  }

  public CreateSubscriptionRequest throttling(Integer throttling) {
    this.throttling = throttling;
    return this;
  }

   /**
   * 
   * @return throttling
  **/
  @ApiModelProperty(example = "5", required = true, value = "")
  public Integer getThrottling() {
    return throttling;
  }

  public void setThrottling(Integer throttling) {
    this.throttling = throttling;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateSubscriptionRequest createSubscriptionRequest = (CreateSubscriptionRequest) o;
    return Objects.equals(this.description, createSubscriptionRequest.description) &&
        Objects.equals(this.subject, createSubscriptionRequest.subject) &&
        Objects.equals(this.notification, createSubscriptionRequest.notification) &&
        Objects.equals(this.expires, createSubscriptionRequest.expires) &&
        Objects.equals(this.throttling, createSubscriptionRequest.throttling);
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, subject, notification, expires, throttling);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateSubscriptionRequest {\n");
    
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    subject: ").append(toIndentedString(subject)).append("\n");
    sb.append("    notification: ").append(toIndentedString(notification)).append("\n");
    sb.append("    expires: ").append(toIndentedString(expires)).append("\n");
    sb.append("    throttling: ").append(toIndentedString(throttling)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

