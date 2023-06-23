package az.iamusayev.util;

import az.iamusayev.dao.entity.StockEntity;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StockConsolePrinter {

    public void printStockEntities(String title, List<StockEntity> stockEntities) {
        if (!stockEntities.isEmpty()) {
            System.out.println(title + ":");
            for (StockEntity stockEntity : stockEntities) {
                System.out.println(formatStockEntity(stockEntity));
            }
            System.out.println();
        }
    }

    private String formatStockEntity(StockEntity stockEntity) {
        StringBuilder sb = new StringBuilder();
        sb.append("StockEntity {")
                .append("id=").append(stockEntity.getId())
                .append(", symbol='").append(stockEntity.getSymbol()).append('\'')
                .append(", companyName='").append(stockEntity.getCompanyName()).append('\'')
                .append(", primaryExchange='").append(stockEntity.getPrimaryExchange()).append('\'')
                .append('}');
        return sb.toString();
    }
}