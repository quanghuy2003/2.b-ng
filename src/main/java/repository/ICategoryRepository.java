package repository;

import model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryRepository extends JpaRepository<Category,Long> {
}
