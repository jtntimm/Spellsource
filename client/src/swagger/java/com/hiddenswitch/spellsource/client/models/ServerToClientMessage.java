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
import com.hiddenswitch.spellsource.client.models.Emote;
import com.hiddenswitch.spellsource.client.models.Entity;
import com.hiddenswitch.spellsource.client.models.EntityChangeSet;
import com.hiddenswitch.spellsource.client.models.GameActions;
import com.hiddenswitch.spellsource.client.models.GameEvent;
import com.hiddenswitch.spellsource.client.models.GameOver;
import com.hiddenswitch.spellsource.client.models.GameState;
import com.hiddenswitch.spellsource.client.models.MessageType;
import com.hiddenswitch.spellsource.client.models.Timers;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * An envelope for messages from the server during gameplay. 
 */
@ApiModel(description = "An envelope for messages from the server during gameplay. ")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)

public class ServerToClientMessage implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("localPlayerId")
  private Integer localPlayerId = null;

  @JsonProperty("timers")
  private Timers timers = null;

  @JsonProperty("messageType")
  private MessageType messageType = null;

  @JsonProperty("changes")
  private EntityChangeSet changes = null;

  @JsonProperty("gameState")
  private GameState gameState = null;

  @JsonProperty("actions")
  private GameActions actions = null;

  @JsonProperty("emote")
  private Emote emote = null;

  @JsonProperty("gameOver")
  private GameOver gameOver = null;

  @JsonProperty("isReplayMessage")
  private Boolean isReplayMessage = false;

  @JsonProperty("startingCards")
  private List<Entity> startingCards = null;

  @JsonProperty("event")
  private GameEvent event = null;

  public ServerToClientMessage id(String id) {
    this.id = id;
    return this;
  }

   /**
   * An optional ID used to disambiguate multiple client replies. Include this ID in the repliesTo field of your ClientToServerMessage if this field is not null. 
   * @return id
  **/
  @ApiModelProperty(value = "An optional ID used to disambiguate multiple client replies. Include this ID in the repliesTo field of your ClientToServerMessage if this field is not null. ")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ServerToClientMessage localPlayerId(Integer localPlayerId) {
    this.localPlayerId = localPlayerId;
    return this;
  }

   /**
   * The ID of the player that corresponds to the local player (the recipient). 
   * @return localPlayerId
  **/
  @ApiModelProperty(value = "The ID of the player that corresponds to the local player (the recipient). ")
  public Integer getLocalPlayerId() {
    return localPlayerId;
  }

  public void setLocalPlayerId(Integer localPlayerId) {
    this.localPlayerId = localPlayerId;
  }

  public ServerToClientMessage timers(Timers timers) {
    this.timers = timers;
    return this;
  }

   /**
   * Get timers
   * @return timers
  **/
  @ApiModelProperty(value = "")
  public Timers getTimers() {
    return timers;
  }

  public void setTimers(Timers timers) {
    this.timers = timers;
  }

  public ServerToClientMessage messageType(MessageType messageType) {
    this.messageType = messageType;
    return this;
  }

   /**
   * Get messageType
   * @return messageType
  **/
  @ApiModelProperty(required = true, value = "")
  public MessageType getMessageType() {
    return messageType;
  }

  public void setMessageType(MessageType messageType) {
    this.messageType = messageType;
  }

  public ServerToClientMessage changes(EntityChangeSet changes) {
    this.changes = changes;
    return this;
  }

   /**
   * Get changes
   * @return changes
  **/
  @ApiModelProperty(value = "")
  public EntityChangeSet getChanges() {
    return changes;
  }

  public void setChanges(EntityChangeSet changes) {
    this.changes = changes;
  }

  public ServerToClientMessage gameState(GameState gameState) {
    this.gameState = gameState;
    return this;
  }

   /**
   * Get gameState
   * @return gameState
  **/
  @ApiModelProperty(value = "")
  public GameState getGameState() {
    return gameState;
  }

  public void setGameState(GameState gameState) {
    this.gameState = gameState;
  }

  public ServerToClientMessage actions(GameActions actions) {
    this.actions = actions;
    return this;
  }

   /**
   * Get actions
   * @return actions
  **/
  @ApiModelProperty(value = "")
  public GameActions getActions() {
    return actions;
  }

  public void setActions(GameActions actions) {
    this.actions = actions;
  }

  public ServerToClientMessage emote(Emote emote) {
    this.emote = emote;
    return this;
  }

   /**
   * Get emote
   * @return emote
  **/
  @ApiModelProperty(value = "")
  public Emote getEmote() {
    return emote;
  }

  public void setEmote(Emote emote) {
    this.emote = emote;
  }

  public ServerToClientMessage gameOver(GameOver gameOver) {
    this.gameOver = gameOver;
    return this;
  }

   /**
   * Get gameOver
   * @return gameOver
  **/
  @ApiModelProperty(value = "")
  public GameOver getGameOver() {
    return gameOver;
  }

  public void setGameOver(GameOver gameOver) {
    this.gameOver = gameOver;
  }

  public ServerToClientMessage isReplayMessage(Boolean isReplayMessage) {
    this.isReplayMessage = isReplayMessage;
    return this;
  }

   /**
   * True iff this message is a part of a replay. 
   * @return isReplayMessage
  **/
  @ApiModelProperty(value = "True iff this message is a part of a replay. ")
  public Boolean isIsReplayMessage() {
    return isReplayMessage;
  }

  public void setIsReplayMessage(Boolean isReplayMessage) {
    this.isReplayMessage = isReplayMessage;
  }

  public ServerToClientMessage startingCards(List<Entity> startingCards) {
    this.startingCards = startingCards;
    return this;
  }

  public ServerToClientMessage addStartingCardsItem(Entity startingCardsItem) {
    if (this.startingCards == null) {
      this.startingCards = new ArrayList<>();
    }
    this.startingCards.add(startingCardsItem);
    return this;
  }

   /**
   * Used for a mulligan request. An array of entities representing the cards you may mulligan. 
   * @return startingCards
  **/
  @ApiModelProperty(value = "Used for a mulligan request. An array of entities representing the cards you may mulligan. ")
  public List<Entity> getStartingCards() {
    return startingCards;
  }

  public void setStartingCards(List<Entity> startingCards) {
    this.startingCards = startingCards;
  }

  public ServerToClientMessage event(GameEvent event) {
    this.event = event;
    return this;
  }

   /**
   * Get event
   * @return event
  **/
  @ApiModelProperty(value = "")
  public GameEvent getEvent() {
    return event;
  }

  public void setEvent(GameEvent event) {
    this.event = event;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ServerToClientMessage serverToClientMessage = (ServerToClientMessage) o;
    return Objects.equals(this.id, serverToClientMessage.id) &&
        Objects.equals(this.localPlayerId, serverToClientMessage.localPlayerId) &&
        Objects.equals(this.timers, serverToClientMessage.timers) &&
        Objects.equals(this.messageType, serverToClientMessage.messageType) &&
        Objects.equals(this.changes, serverToClientMessage.changes) &&
        Objects.equals(this.gameState, serverToClientMessage.gameState) &&
        Objects.equals(this.actions, serverToClientMessage.actions) &&
        Objects.equals(this.emote, serverToClientMessage.emote) &&
        Objects.equals(this.gameOver, serverToClientMessage.gameOver) &&
        Objects.equals(this.isReplayMessage, serverToClientMessage.isReplayMessage) &&
        Objects.equals(this.startingCards, serverToClientMessage.startingCards) &&
        Objects.equals(this.event, serverToClientMessage.event);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, localPlayerId, timers, messageType, changes, gameState, actions, emote, gameOver, isReplayMessage, startingCards, event);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServerToClientMessage {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    localPlayerId: ").append(toIndentedString(localPlayerId)).append("\n");
    sb.append("    timers: ").append(toIndentedString(timers)).append("\n");
    sb.append("    messageType: ").append(toIndentedString(messageType)).append("\n");
    sb.append("    changes: ").append(toIndentedString(changes)).append("\n");
    sb.append("    gameState: ").append(toIndentedString(gameState)).append("\n");
    sb.append("    actions: ").append(toIndentedString(actions)).append("\n");
    sb.append("    emote: ").append(toIndentedString(emote)).append("\n");
    sb.append("    gameOver: ").append(toIndentedString(gameOver)).append("\n");
    sb.append("    isReplayMessage: ").append(toIndentedString(isReplayMessage)).append("\n");
    sb.append("    startingCards: ").append(toIndentedString(startingCards)).append("\n");
    sb.append("    event: ").append(toIndentedString(event)).append("\n");
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
