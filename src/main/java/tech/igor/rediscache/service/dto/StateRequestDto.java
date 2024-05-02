package tech.igor.rediscache.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StateRequestDto(
    @JsonProperty("nome") String name,
    @JsonProperty("sigla") String stateCode
) {}
