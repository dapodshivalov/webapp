package repository.filter;

import entity.filter.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepo extends CrudRepository<Country, Integer> {
    List<Country> findAll();
}
