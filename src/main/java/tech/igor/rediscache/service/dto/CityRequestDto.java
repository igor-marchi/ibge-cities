package tech.igor.rediscache.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CityRequestDto(
    @JsonProperty("nome") String name,
    @JsonProperty("codigo_ibge") String code
) {}
