package az.iamusayev.dao.entity;

import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@FieldDefaults(level = PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stocks")
public class StockEntity {
    @Id
    Long id;
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
    @Column(value = "week52_High")
    Double week52High;
    @Column(value = "week52_Low")
    Double week52Low;
    Double ytdChange;
    Long lastTradeTime;
    String currency;
    @Column(value = "isusmarket_open")
    Boolean isUSMarketOpen;
}