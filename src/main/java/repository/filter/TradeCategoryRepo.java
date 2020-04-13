package repository.filter;

import entity.filter.TradeCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeCategoryRepo extends CrudRepository<TradeCategory, Integer> {
    List<TradeCategory> findAll();
}
