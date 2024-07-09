package org.example.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommandMessageDto {
    @JsonProperty private String gameId;
    @JsonProperty private String objId;
    @JsonProperty private String operationId;
    @JsonProperty
    @JsonIgnoreProperties(ignoreUnknown = true)
    private JSONObject args;

    public CommandMessageDto() {
    }

    public CommandMessageDto(String gameId, String objId, String operationId, JSONObject args) {
        this.gameId = gameId;
        this.objId = objId;
        this.operationId = operationId;
        this.args = args;
    }

    public JSONObject getArgs() {
        return args;
    }

    public void setArgs(JSONObject args) {
        this.args = args;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }
}
