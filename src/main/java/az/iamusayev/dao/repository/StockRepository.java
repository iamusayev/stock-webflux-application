package az.iamusayev.dao.repository;

import az.iamusayev.dao.entity.StockEntity;
import java.util.List;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface StockRepository extends R2dbcRepository<StockEntity, Long> {

    @Query("""
                SELECT s.company_name, s.symbol, s.primary_exchange
                FROM stocks s
                INNER JOIN (SELECT symbol, MAX(latest_update) as max_latest_update
                FROM stocks
                GROUP BY symbol) sm ON s.symbol = sm.symbol AND s.latest_update = sm.max_latest_update
                ORDER BY CASE
                WHEN s.volume IS NOT NULL THEN s.volume
                ELSE s.previous_volume
                END DESC, s.company_name ASC
                LIMIT 5
            """)
    Flux<StockEntity> findTop5StocksWithHighestValue();


    @Query("""
                 SELECT s.company_name, s.symbol, s.primary_exchange
                 FROM stocks s
                 INNER JOIN (SELECT symbol, MAX(latest_update) as max_latest_update
                 FROM stocks
                 GROUP BY symbol) sm ON s.symbol = sm.symbol AND s.latest_update = sm.max_latest_update
                 ORDER BY (s.latest_price - s.previous_close) / s.previous_close DESC
                 LIMIT 5
            """)
    Flux<StockEntity> findTop5StocksWithGreatestChangePercent();

    Flux<StockEntity> findBySymbolIn(List<String> symbols);

}