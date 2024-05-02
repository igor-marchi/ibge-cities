package tech.igor.rediscache.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class City implements Serializable {
    private String name;

    @JsonProperty("ibge_code")
    private String code;

    public City() {}

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
