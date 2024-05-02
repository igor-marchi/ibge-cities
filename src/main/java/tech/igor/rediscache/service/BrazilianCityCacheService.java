package tech.igor.rediscache.service;

import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import tech.igor.rediscache.entity.City;

import java.util.List;

@Service
public class BrazilianCityCacheService {
    @CachePut(value = "cities", key = "#stateCode")
    public List<City> cacheBrazilianCity(String stateCode, List<City> cities) {
        System.out.println("Caching cities for state code " + stateCode);
        return cities;
    }
}
