package repository;

import entity.CountryIds;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryIdsRepo extends CrudRepository<CountryIds, CountryIds>, JpaSpecificationExecutor<CountryIds> {
    List<CountryIds> findAll();
}
