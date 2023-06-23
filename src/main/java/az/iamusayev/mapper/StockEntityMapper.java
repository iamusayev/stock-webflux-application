package az.iamusayev.mapper;

import az.iamusayev.client.model.DetailedStockData;
import az.iamusayev.dao.entity.StockEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockEntityMapper {

    @InheritConfiguration
    StockEntity map(DetailedStockData stockSymbol);
}