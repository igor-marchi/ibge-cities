package tech.igor.rediscache.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tech.igor.rediscache.entity.City;
import tech.igor.rediscache.entity.State;
import tech.igor.rediscache.service.BrazilianCityCacheService;
import tech.igor.rediscache.service.BrazilianCityService;
import tech.igor.rediscache.service.BrazilianStateService;

import java.time.Duration;
import java.util.List;

@Component
public class BrazilianCityCacheJob {
    private final BrazilianCityCacheService brazilianCityCacheService;
    private final BrazilianCityService brazilianCityService;
    private final BrazilianStateService brazilianStateService;

    public BrazilianCityCacheJob(
        BrazilianCityCacheService brazilianCityCacheService,
        BrazilianCityService brazilianCityService,
        BrazilianStateService brazilianStateService
    ) {
        this.brazilianCityCacheService = brazilianCityCacheService;
        this.brazilianCityService = brazilianCityService;
        this.brazilianStateService = brazilianStateService;
    }

    @Scheduled(cron = "0 0 2 * * TUE")
    public void cacheBrazilianCityJob() {
        List<State> states = brazilianStateService.get();

        states.forEach(state -> {
            String stateCode = state.getStateCode();
            List<City> cities = brazilianCityService.get(stateCode);
            if (cities.isEmpty()) {
                System.out.println("No cities found for state code " + stateCode);
                return;
            }

            brazilianCityCacheService.cacheBrazilianCity(stateCode, cities);
            System.out.println("Cities cached for state code " + stateCode);

           sleep(Duration.ofMillis(10_000));
        });

        System.out.println("Cities cached");
    }

    private void sleep(Duration duration) {
        try {
            System.out.println("Sleeping for " + duration.toMillis() + " milliseconds");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted ");
        }
    }
}
