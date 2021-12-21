package repository;

import model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepository extends JpaRepository<Product,Long> {
Iterable<Product> findByName(String name);
Iterable<Product>findAllByOrderByName();
}
