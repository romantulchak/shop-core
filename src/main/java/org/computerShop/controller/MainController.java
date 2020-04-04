package org.computerShop.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.model.Category;
import org.computerShop.model.Product;
import org.computerShop.model.Views;
import org.computerShop.model.accessory.CPU;
import org.computerShop.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("/api/products")
public class MainController {

    private ProductServiceImpl productService;

    @Autowired
    public MainController(ProductServiceImpl productService){
        this.productService = productService;
    }

    @GetMapping
    @JsonView(Views.ProductFull.class)
    public List<Product> main(){
        return productService.allProducts();
    }

    @GetMapping("/filterByPrice")
    @JsonView(Views.ProductFull.class)
    public List<Product> filterByPrice(){
        return productService.filterByPrice();
    }


    @PostMapping("/createProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createProduct(@RequestBody Product product){
            return productService.createProduct(product);
    }
    @DeleteMapping("/deleteProduct/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Product product){
        return this.productService.deleteProduct(product);
    }
    @PostMapping("/pushImage")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @PreAuthorize("hasRole('ADMIN')")
    public void saveImage(@RequestParam("file") MultipartFile[] files) throws Exception{
        productService.pushImage(files);
    }

    @GetMapping("/details/{id}")
    @JsonView(Views.ProductFull.class)
    public Product detailsProduct(@PathVariable("id") Product product){
        return productService.detailsProduct(product);
    }

    @GetMapping("/filterByCategory")
    @JsonView(Views.ProductFull.class)
    public List<Product> productsByCategory(@RequestParam(value = "categoryName", required = false) String category){
        return productService.filterByCategory(category);
    }

    @GetMapping("/filter")
    @JsonView(Views.ProductFull.class)
    public List<Product> filter(@RequestParam(value = "brands", required = false) String[] brands){
        return productService.filter(brands);
    }

    @GetMapping("/cpus")
    @JsonView(Views.ProductFull.class)
    public List<CPU> getAllCpus(){
        System.out.println("sa");
        return productService.getAllCpus();
    }

    @PostMapping("/createCpu")
    public ResponseEntity<String> createCpu(@RequestBody CPU cpu){
        return productService.createCpu(cpu);
    }

}
