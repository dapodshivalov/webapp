package repository.filter;

import entity.filter.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends CrudRepository<Item, Integer> {
    List<Item> findAll();
}
