package repository;

import entity.ItemIds;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemIdsRepo extends CrudRepository<ItemIds, Integer>, JpaSpecificationExecutor<ItemIds> {
    List<ItemIds> findAll();
}
