package repository.filter;

import entity.filter.Section;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepo extends CrudRepository<Section, Integer> {
    List<Section> findAll();
}
