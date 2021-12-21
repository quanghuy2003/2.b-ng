package service;

import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import repository.ICategoryRepository;
import repository.IProductRepository;

import java.util.Optional;

public class CategoryService implements ICategoryService{

    @Autowired
    private ICategoryRepository iCategoryRepository;

    @Override
    public Iterable<Category> findAll() {
        return iCategoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return iCategoryRepository.findById(id);
    }

    @Override
    public void save(Category category) {
        iCategoryRepository.save(category);
    }

    @Override
    public void remove(Long id) {
        iCategoryRepository.deleteById(id);
    }
}
