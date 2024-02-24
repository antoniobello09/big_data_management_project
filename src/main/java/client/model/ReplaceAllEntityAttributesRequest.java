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
 * ReplaceAllEntityAttributesRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2024-02-23T12:12:13.839+01:00")
public class ReplaceAllEntityAttributesRequest {
  @SerializedName("temperature")
  private Object temperature = null;

  @SerializedName("seatNumber")
  private Object seatNumber = null;

  public ReplaceAllEntityAttributesRequest temperature(Object temperature) {
    this.temperature = temperature;
    return this;
  }

   /**
   * 
   * @return temperature
  **/
  @ApiModelProperty(example = "{\"value\":25.5}", required = true, value = "")
  public Object getTemperature() {
    return temperature;
  }

  public void setTemperature(Object temperature) {
    this.temperature = temperature;
  }

  public ReplaceAllEntityAttributesRequest seatNumber(Object seatNumber) {
    this.seatNumber = seatNumber;
    return this;
  }

   /**
   * 
   * @return seatNumber
  **/
  @ApiModelProperty(example = "{\"value\":6}", required = true, value = "")
  public Object getSeatNumber() {
    return seatNumber;
  }

  public void setSeatNumber(Object seatNumber) {
    this.seatNumber = seatNumber;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReplaceAllEntityAttributesRequest replaceAllEntityAttributesRequest = (ReplaceAllEntityAttributesRequest) o;
    return Objects.equals(this.temperature, replaceAllEntityAttributesRequest.temperature) &&
        Objects.equals(this.seatNumber, replaceAllEntityAttributesRequest.seatNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(temperature, seatNumber);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReplaceAllEntityAttributesRequest {\n");
    
    sb.append("    temperature: ").append(toIndentedString(temperature)).append("\n");
    sb.append("    seatNumber: ").append(toIndentedString(seatNumber)).append("\n");
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

