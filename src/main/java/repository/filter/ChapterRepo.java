package repository.filter;

import entity.filter.Chapter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepo extends CrudRepository<Chapter, Integer> {
    List<Chapter> findAll();
}
