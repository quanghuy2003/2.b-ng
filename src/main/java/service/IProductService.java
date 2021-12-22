package service;

import model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService extends IGeneralService<Product> {
    Iterable<Product> findByName(String name);
    Iterable<Product>findAllByOrderByName();
    Page<Product> findAll(Pageable pageable);
    Page<Product> findAllByNameContaining(String name, Pageable pageable);

}
