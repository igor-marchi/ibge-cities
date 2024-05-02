package tech.igor.rediscache.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tech.igor.rediscache.entity.State;
import tech.igor.rediscache.service.dto.StateRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrazilianStateService {

    private final RestClient restClient;

    public BrazilianStateService(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("https://brasilapi.com.br/api/ibge/uf/v1").build();
    }
    public List<State> get() {
        List<StateRequestDto> states = restClient
                .get()
                .retrieve()
                .body(new ParameterizedTypeReference<List<StateRequestDto>>() {
                });

        if (states == null) return List.of();

        return states.stream().map(state -> new State(state.name(), state.stateCode())).collect(Collectors.toList());
    }
}
