package az.iamusayev.client.model;

import static lombok.AccessLevel.PRIVATE;

import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = PRIVATE)
public class DetailedStockData {
    String symbol;
    String companyName;
    String primaryExchange;
    String calculationPrice;
    Double open;
    Long openTime;
    String openSource;
    Double close;
    Long closeTime;
    String closeSource;
    Double high;
    Long highTime;
    String highSource;
    Double low;
    Long lowTime;
    String lowSource;
    Double latestPrice;
    String latestSource;
    String latestTime;
    Long latestUpdate;
    Long latestVolume;
    Double iexRealtimePrice;
    Integer iexRealtimeSize;
    Long iexLastUpdated;
    Double delayedPrice;
    Long delayedPriceTime;
    Double oddLotDelayedPrice;
    Long oddLotDelayedPriceTime;
    Double extendedPrice;
    Double extendedChange;
    Double extendedChangePercent;
    Long extendedPriceTime;
    Double previousClose;
    Long previousVolume;
    Double change;
    Double changePercent;
    Long volume;
    Double iexMarketPercent;
    Long iexVolume;
    Double iexBidPrice;
    Integer iexBidSize;
    Double iexAskPrice;
    Integer iexAskSize;
    Double iexOpen;
    Long iexOpenTime;
    Double iexClose;
    Long iexCloseTime;
    Long marketCap;
    Double peRatio;
    Double week52High;
    Double week52Low;
    Double ytdChange;
    Long lastTradeTime;
    String currency;
    Boolean isUSMarketOpen;
}