package az.iamusayev.scheduler;

import az.iamusayev.service.StockMarketDataService;
import az.iamusayev.util.StockConsolePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockConsolePrinterScheduler {

    private final StockMarketDataService stockMarketDataService;

    @Scheduled(cron = "0 */15 * * * *")
    public void updateStocksWhenVolumeChanges() {
        stockMarketDataService.updateStocksWhenVolumeChanges();
    }

    @Scheduled(fixedRate = 5000)
    public void printTop5HighestValueStocks() {
        var top5MostExpensiveStocks = stockMarketDataService.getTop5HighestValueStocks();
        StockConsolePrinter.printStockEntities("Top 5 Most Expensive Stocks", top5MostExpensiveStocks);
    }

    @Scheduled(fixedRate = 5000)
    public void printTop5MostChangedStocks() {
        var top5MostChangedStocks = stockMarketDataService.getTop5GreatestChangePercent();
        StockConsolePrinter.printStockEntities("Top 5 Most Changed Stocks", top5MostChangedStocks);
    }
}