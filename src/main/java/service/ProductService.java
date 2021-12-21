package service;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import repository.IProductRepository;

import java.util.Optional;

public class ProductService implements IProductService{

@Autowired
private IProductRepository iProductRepository;
    @Override
    public Iterable<Product> findAll() {
        return iProductRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return iProductRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        iProductRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        iProductRepository.deleteById(id);
    }

    @Override
    public Iterable<Product> findByName(String name) {
        return iProductRepository.findByName(name);
    }

    @Override
    public Iterable<Product> findAllByOrderByName() {
        return iProductRepository.findAllByOrderByName();
    }
}
