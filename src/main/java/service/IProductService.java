package service;

import model.Product;

public interface IProductService extends IGeneralService<Product> {
    Iterable<Product> findByName(String name);
    Iterable<Product>findAllByOrderByName();
}
