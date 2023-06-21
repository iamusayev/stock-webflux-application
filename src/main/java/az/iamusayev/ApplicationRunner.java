package az.iamusayev;

import az.iamusayev.service.StockMarketDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApplicationRunner implements CommandLineRunner {

    @Autowired
    private StockMarketDataService stockMarketDataService;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRunner.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        stockMarketDataService.saveFirstFiveHundredStocks();
    }
}