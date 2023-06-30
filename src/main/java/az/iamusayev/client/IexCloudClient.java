package az.iamusayev.client;

import static az.iamusayev.model.constants.ExceptionConstants.IEX_CLOUD_CLIENT_EXCEPTION_MESSAGE;

import az.iamusayev.client.model.DetailedStockData;
import az.iamusayev.client.model.StockSymbol;
import az.iamusayev.config.WebClientConfig;
import az.iamusayev.exception.IexCloudClientException;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class IexCloudClient {

    private final WebClientConfig webClientConfig;
    private final String token;

    public IexCloudClient(final WebClientConfig webClientConfig,
                          @Value("${api.token}") final String token) {
        this.webClientConfig = webClientConfig;
        this.token = token;
    }



    public Flux<StockSymbol> getStockSymbols() {
        try {
            return webClientConfig.webClient()
                    .get()
                    .uri(uri -> uri.path("stable/ref-data/symbols")
                                   .queryParam("token", token)
                                   .build())
                    .retrieve()
                    .bodyToFlux(StockSymbol.class);
        } catch (Exception e) {
            throw new IexCloudClientException(IEX_CLOUD_CLIENT_EXCEPTION_MESSAGE);
        }
    }


    public Flux<DetailedStockData> getDetailedStockDataBySymbol(String symbol) {
        try {
            return webClientConfig.webClient()
                    .get()
                    .uri(uri -> uri.path("stable/stock/{symbol}/quote")
                                   .queryParam("token", token)
                                   .build(symbol))
                                   .retrieve()
                    .bodyToFlux(DetailedStockData.class);
        } catch (Exception e) {
            throw new IexCloudClientException(IEX_CLOUD_CLIENT_EXCEPTION_MESSAGE);
        }
    }
}