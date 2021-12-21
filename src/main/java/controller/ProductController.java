package controller;


import model.Category;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.ICategoryService;
import service.IProductService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("category")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    @GetMapping("/create")
    public String showFormCreate() {
        return "/create";
    }

    @PostMapping("/create")
    public String create(Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("")
    public String showList(Model model, String key) {
//        Iterable<Product> productIterable = productService.findAll();
//        model.addAttribute("products", productIterable);
//        return "/list";
        List<Product> productList;
        if (key != null) {
            productList = (List<Product>) productService.findByName(key);
        } else {
            productList = (List<Product>) productService.findAll();
        }
        model.addAttribute("products", productList);
        return "/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.remove(id);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Product> product = productService.findById(id);
        Product product1 = product.get();
        model.addAttribute("product", product1);
        return "/edit";
    }

    @PostMapping("/edit")
    public String edit1(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/sort")
    public String showSort(Model model, String key) {
        List<Product> productList;
        productList = (List<Product>) productService.findAllByOrderByName();
        model.addAttribute("product",productList);
        return "/list";
    }
}
