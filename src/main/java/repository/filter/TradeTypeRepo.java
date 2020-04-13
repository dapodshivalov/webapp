package repository.filter;

import entity.filter.TradeType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeTypeRepo extends CrudRepository<TradeType, Integer> {
    List<TradeType> findAll();
}
