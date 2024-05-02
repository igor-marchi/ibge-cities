package tech.igor.rediscache.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.igor.rediscache.entity.City;
import tech.igor.rediscache.entity.State;
import tech.igor.rediscache.service.BrazilianCityService;
import tech.igor.rediscache.service.BrazilianStateService;
import tech.igor.rediscache.service.dto.StateRequestDto;

import java.util.List;

@RestController
@RequestMapping("/brazilian")
public class BrazilianController {
    private final BrazilianCityService brazilianCityService;
    private final BrazilianStateService brazilianStateService;

    public BrazilianController(BrazilianCityService brazilianCityService, BrazilianStateService brazilianStateService) {
        this.brazilianCityService = brazilianCityService;
        this.brazilianStateService = brazilianStateService;
    }

    @GetMapping("/cities/{stateCode}")
    public ResponseEntity<List<City>> getBrazilianCity(@PathVariable String stateCode) {
        List<City> cities = brazilianCityService.get(stateCode);
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/states")
    public ResponseEntity<List<State>> getBrazilianState() {
        List<State> states = brazilianStateService.get();
        return ResponseEntity.ok(states);
    }
}
