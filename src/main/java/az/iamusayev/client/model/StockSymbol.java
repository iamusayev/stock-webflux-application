package az.iamusayev.client.model;

import static lombok.AccessLevel.PRIVATE;

import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = PRIVATE)
public class StockSymbol {
    String symbol;
    String name;
    String date;
    String type;
    String iexId;
    String region;
    String currency;
    Boolean isEnabled;
    String figi;
    String cik;
}