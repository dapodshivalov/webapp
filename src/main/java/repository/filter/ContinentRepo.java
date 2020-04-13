package repository.filter;

import entity.filter.Continent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContinentRepo extends CrudRepository<Continent, Integer> {
    List<Continent> findAll();
}
