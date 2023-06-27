package az.iamusayev.service;

import static az.iamusayev.model.constants.ExceptionConstants.STOCK_DATA_PROCESSING_EXCEPTION_MESSAGE;

import az.iamusayev.client.IexCloudClient;
import az.iamusayev.client.model.DetailedStockData;
import az.iamusayev.client.model.StockSymbol;
import az.iamusayev.dao.entity.StockEntity;
import az.iamusayev.dao.repository.StockRepository;
import az.iamusayev.exception.StockDataProcessingException;
import az.iamusayev.mapper.StockEntityMapper;
import az.iamusayev.util.NullSafetyChecker;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
@RequiredArgsConstructor
public class StockMarketDataService {

    private final IexCloudClient iexCloudClient;
    private final StockRepository stockRepository;
    private final StockEntityMapper stockEntityMapper;

    public void saveFirstFiveHundredStocks() {
        log.info("saveFirstFiveHundredStock() method called");

        int batchSize = 25;
        for (int i = 0; i < batchSize; i++) {

            try {
                Thread.sleep(40000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            List<String> enabledSymbols = fetchEnabledStockSymbols(i * batchSize);
            List<Flux<DetailedStockData>> detailedStockDataFluxes = fetchDetailedStockData(enabledSymbols);

            Flux<StockEntity> stockEntityFlux = Flux.fromIterable(detailedStockDataFluxes)
                                                    .flatMapSequential(flux -> flux.map(stockEntityMapper::map));

            stockRepository.saveAll(stockEntityFlux).collectList().subscribe(
                    it -> {
                    },
                    throwable -> {
                        log.error("Error occurred while updating stock entities");
                        throw new StockDataProcessingException(STOCK_DATA_PROCESSING_EXCEPTION_MESSAGE);
                    }
            );
        }

        log.info("saveFirstFiveHundredStock() method finished");
    }

    public void updateStocksWhenVolumeChanges() {
        log.info("updateStocksWhenVolumeChanges() method called");

        int batchSize = 25;
        for (int i = 0; i < batchSize; i++) {

            try {
                Thread.sleep(40000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            List<String> stockSymbols = fetchEnabledStockSymbols(i * batchSize);
            List<Flux<DetailedStockData>> stockDataFluxList = fetchDetailedStockData(stockSymbols);
            List<StockEntity> existingStockEntities = stockRepository.findBySymbolIn(stockSymbols).collectList().block();
            List<StockEntity> updatedStockEntities = new ArrayList<>();

            List<StockEntity> fetchedStockEntities = Flux.fromIterable(stockDataFluxList)
                                                         .flatMapSequential(flux -> flux.map(stockEntityMapper::map))
                                                         .collectList()
                                                         .block();

            NullSafetyChecker.ensureNonNull(fetchedStockEntities, existingStockEntities);

            for (StockEntity fetchedEntity : fetchedStockEntities) {
                for (StockEntity existingEntity : existingStockEntities) {
                    if (Objects.equals(fetchedEntity.getSymbol(), existingEntity.getSymbol()) &&
                            !Objects.equals(fetchedEntity.getPreviousVolume(), existingEntity.getPreviousVolume())) {
                        updatedStockEntities.add(fetchedEntity);
                    }
                }
            }

            stockRepository.saveAll(updatedStockEntities)
                    .subscribe(it -> {
                    }, throwable -> {
                        log.error("Error occurred while updating stock entities");
                        throw new StockDataProcessingException(STOCK_DATA_PROCESSING_EXCEPTION_MESSAGE);
                    });
        }

        log.info("updateStocksWhenVolumeChanges() method finished");
    }

    public List<StockEntity> getTop5HighestValueStocks() {
        return stockRepository.findTop5StocksWithHighestValue()
                              .collectList()
                              .block();
    }


    public List<StockEntity> getTop5GreatestChangePercent() {
        return stockRepository.findTop5StocksWithGreatestChangePercent()
                              .collectList()
                              .block();
    }


    private List<Flux<DetailedStockData>> fetchDetailedStockData(List<String> symbols) {
        return symbols.parallelStream()
                .map(iexCloudClient::getDetailedStockDataBySymbol)
                .toList();
    }


    private List<String> fetchEnabledStockSymbols(int offset) {
        return iexCloudClient.getStockSymbols()

                .skip(offset)
                .filter(StockSymbol::getIsEnabled)
                .map(StockSymbol::getSymbol)
                .filter(Objects::nonNull)
    }
}