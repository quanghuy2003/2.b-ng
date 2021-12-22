package repository;

import model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Iterable<Product> findByName(String name);

    Iterable<Product> findAllByOrderByName();

    Page<Product> findAll(Pageable pageable);

    Page<Product> findAllByNameContaining(String name, Pageable pageable);

}
