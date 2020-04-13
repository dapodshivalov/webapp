package repository;

import entity.Trade;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TradeRepo extends CrudRepository<Trade, Integer>, JpaSpecificationExecutor<Trade> {
    List<Trade> findAll();
}
