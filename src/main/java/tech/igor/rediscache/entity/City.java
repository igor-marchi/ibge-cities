package tech.igor.rediscache.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class City {
    
    private final String name;

    @JsonProperty("ibge_code")
    private final String code;

    public City(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
