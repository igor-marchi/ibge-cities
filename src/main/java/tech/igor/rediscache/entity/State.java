package tech.igor.rediscache.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class State {
    private final String name;

    @JsonProperty("state_code")
    private final String stateCode;

    public State(String name, String stateCode) {
        this.name = name;
        this.stateCode = stateCode;
    }

    public String getName() {
        return name;
    }

    public String getStateCode() {
        return stateCode;
    }
}
