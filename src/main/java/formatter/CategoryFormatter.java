package formatter;


import model.Category;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import service.ICategoryService;
import service.IProductService;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class CategoryFormatter implements Formatter<Category>{
    private ICategoryService iCategoryService;

    @Autowired
    public CategoryFormatter(ICategoryService iCategoryService) {
        this.iCategoryService = iCategoryService;
    }

    @Override
    public Category parse(String text, Locale locale) throws ParseException {
        Optional<Category> provinceOptional = iCategoryService.findById(Long.parseLong(text));
        return provinceOptional.orElse(null);
    }

    @Override
    public String print(Category object, Locale locale) {
        return "[" + object.getId() + ", " +object.getName() + "]";
    }
}
