package tech.igor.rediscache.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tech.igor.rediscache.entity.City;
import tech.igor.rediscache.service.dto.CityRequestDto;
import tech.igor.rediscache.service.dto.StateRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrazilianCityService {
    private final RestClient restClient;

    public BrazilianCityService(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("https://brasilapi.com.br/api/ibge/municipios/v1").build();
    }

    @Cacheable(value = "cities", key = "#stateCode")
    public List<City> get(String stateCode) {
        List<CityRequestDto> cities = restClient
            .get()
            .uri("/{stateCode}", stateCode)
            .retrieve()
            .body(new ParameterizedTypeReference<List<CityRequestDto>>() {});

        if (cities == null) return List.of();

        return cities.stream().map(city -> new City(city.name(), city.code())).collect(Collectors.toList());
    }
}
