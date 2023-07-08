package az.iamusayev.config;

import az.iamusayev.service.StockMarketDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class CommaLinerConfig {

    @Bean
    public CommandLineRunner commandLineRunner(StockMarketDataService stockMarketDataService) {
        return args -> {
            log.error("CommandLineRunner is running now");
            stockMarketDataService.saveFirstFiveHundredStocks();
        };
    }
}