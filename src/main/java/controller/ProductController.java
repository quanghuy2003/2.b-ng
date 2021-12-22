package controller;


import model.Category;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.ICategoryService;
import service.IProductService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Value("${file-upload}")
    private String fileUpload;

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("category")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

//    @GetMapping("/create")
//    public String showFormCreate() {
//        return "/create";
//    }
//
//    @PostMapping("/create")
//    public String create(Product product) {
//        productService.save(product);
//        return "redirect:/products";
//    }

    @GetMapping("create")
    public String showCreate(Model model) {
        model.addAttribute("product",new Product());
        return "/create";
    }
    @PostMapping("create")
    public String createProduct(Product product, @RequestParam MultipartFile image) {
        String fileName = image.getOriginalFilename();
        try {
            FileCopyUtils.copy(image.getBytes(),
                    new File("D:\\test\\" + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        product.setImg(fileName);
        productService.save(product);
        return "redirect:/products";
    }

//    @GetMapping("")
//    public String showList(Model model, String key) {
////        Iterable<Product> productIterable = productService.findAll();
////        model.addAttribute("products", productIterable);
////        return "/list";
//        List<Product> productList;
//        if (key != null) {
//            productList = (List<Product>) productService.findByName(key);
//        } else {
//            productList = (List<Product>) productService.findAll();
//        }
//        model.addAttribute("products", productList);
//        return "/list";
//    }

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
        model.addAttribute("products",productList);
        return "/list";
    }

    @GetMapping("")
    public ModelAndView listProducts(@RequestParam("search") Optional<String> search,@PageableDefault(value = 5) Pageable pageable){
//        Page<Product> products= productService.findAll(pageable);
//        ModelAndView modelAndView = new ModelAndView("/list");
//        modelAndView.addObject("products",products);
//        return modelAndView;
        Page<Product>products;
        if (search.isPresent()){
            products=productService.findAllByNameContaining(search.get(),pageable);
        }else {
            products=productService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("products",products);
        return modelAndView;
    }

    @GetMapping("/upload")
    public ModelAndView showCreateIMG() {
        ModelAndView modelAndView = new ModelAndView("/createIMG");
        return modelAndView;
    }


    @PostMapping("/show")
    public ModelAndView saveIMG( MultipartFile image, String imageLink) {
        String fileName = image.getOriginalFilename();
        try {
            FileCopyUtils.copy(image.getBytes(),
                    new File("/D:\\test\\/" + fileName)); // coppy ảnh từ ảnh nhận được vào thư mục quy định,
            // đường dẫn ảo là /nhuanh/
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        ModelAndView modelAndView = new ModelAndView("/index2");
        modelAndView.addObject("src", fileName);
        modelAndView.addObject("srcImg", imageLink);
        return modelAndView;
    }

}
