/*
 * Hidden Switch Spellsource API
 * The Spellsource API for matchmaking, user accounts, collections management and more.  To get started, create a user account and make sure to include the entirety of the returned login token as the X-Auth-Token header. You can reuse this token, or login for a new one.  ClientToServerMessage and ServerToClientMessage are used for the realtime game state and actions two-way websocket interface for actually playing a game. Envelope is used for the realtime API services.  For the routes that correspond to the paths in this file, visit the Gateway.java file in the Spellsource-Server GitHub repository located in this definition file. 
 *
 * OpenAPI spec version: 4.0.1
 * Contact: ben@hiddenswitch.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.hiddenswitch.spellsource.client.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Describes a Unity color. 
 */
@ApiModel(description = "Describes a Unity color. ")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)

public class Color implements Serializable {

  @JsonProperty("r")
  private Float r = 0.0f;

  @JsonProperty("g")
  private Float g = 0.0f;

  @JsonProperty("b")
  private Float b = 0.0f;

  @JsonProperty("a")
  private Float a = 1.0f;

  public Color r(Float r) {
    this.r = r;
    return this;
  }

   /**
   * Red channel in units of 0.0-1.0
   * @return r
  **/
  @ApiModelProperty(required = true, value = "Red channel in units of 0.0-1.0")
  public Float getR() {
    return r;
  }

  public void setR(Float r) {
    this.r = r;
  }

  public Color g(Float g) {
    this.g = g;
    return this;
  }

   /**
   * Green channel in units of 0.0-1.0
   * @return g
  **/
  @ApiModelProperty(required = true, value = "Green channel in units of 0.0-1.0")
  public Float getG() {
    return g;
  }

  public void setG(Float g) {
    this.g = g;
  }

  public Color b(Float b) {
    this.b = b;
    return this;
  }

   /**
   * Blue channel in units of 0.0-1.0
   * @return b
  **/
  @ApiModelProperty(required = true, value = "Blue channel in units of 0.0-1.0")
  public Float getB() {
    return b;
  }

  public void setB(Float b) {
    this.b = b;
  }

  public Color a(Float a) {
    this.a = a;
    return this;
  }

   /**
   * Alpha in units of 0.0-1.0
   * @return a
  **/
  @ApiModelProperty(required = true, value = "Alpha in units of 0.0-1.0")
  public Float getA() {
    return a;
  }

  public void setA(Float a) {
    this.a = a;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Color color = (Color) o;
    return Objects.equals(this.r, color.r) &&
        Objects.equals(this.g, color.g) &&
        Objects.equals(this.b, color.b) &&
        Objects.equals(this.a, color.a);
  }

  @Override
  public int hashCode() {
    return Objects.hash(r, g, b, a);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Color {\n");
    
    sb.append("    r: ").append(toIndentedString(r)).append("\n");
    sb.append("    g: ").append(toIndentedString(g)).append("\n");
    sb.append("    b: ").append(toIndentedString(b)).append("\n");
    sb.append("    a: ").append(toIndentedString(a)).append("\n");
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

