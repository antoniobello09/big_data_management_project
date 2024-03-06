
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
 * CreateEntityRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2024-02-23T12:12:13.839+01:00")
public class CreateEntityRequest {
  @SerializedName("type")
  private String type = null;

  @SerializedName("id")
  private String id = null;

  @SerializedName("plate")
  private Object plate = null;

  @SerializedName("weather")
  private String weather = null;

  public CreateEntityRequest weather(String weather) {
    this.weather = weather;
    return this;
  }

  @ApiModelProperty(example = "{\"weather\":\"cloudy\"}", required = true, value = "")
  public String getWeather() {
    return weather;
  }

  public void setWeather(String weather) {
    this.weather = weather;
  }

  public CreateEntityRequest type(String type) {
    this.type = type;
    return this;
  }

  @ApiModelProperty(example = "Plate", required = true, value = "")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public CreateEntityRequest id(String id) {
    this.id = id;
    return this;
  }

  @ApiModelProperty(example = "001", required = true, value = "")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public CreateEntityRequest plate(Object plate) {
    this.plate = plate;
    return this;
  }

  @ApiModelProperty(example = "{\"value\":AAA0000AA}", required = true, value = "")
  public Object getPlate() {
    return plate;
  }

  public void setPlate(Object plate) {
    this.plate = plate;
  }

  


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateEntityRequest createEntityRequest = (CreateEntityRequest) o;
    return Objects.equals(this.type, createEntityRequest.type) &&
        Objects.equals(this.id, createEntityRequest.id) &&
        Objects.equals(this.plate, createEntityRequest.plate)&&
        Objects.equals(this.weather, createEntityRequest.weather);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id, plate, weather);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateEntityRequest {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    plate number: ").append(toIndentedString(plate)).append("\n");
    sb.append("    weather: ").append(toIndentedString(weather)).append("\n");
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

