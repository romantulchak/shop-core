package org.computerShop.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.model.Category;
import org.computerShop.model.Product;
import org.computerShop.model.Views;
import org.computerShop.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createProduct(@RequestBody Product product){
            return productService.createProduct(product);
    }
    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Product product){
        return this.productService.deleteProduct(product);
    }
    @PostMapping("/pushImage")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)

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
    public List<Product> productsByCategory(@RequestParam(value = "categoryName", required = false)String category){


        return productService.filterByCategory(category);
    }

    @GetMapping("/getProductsById")
    public List<Product> getProductsById(@RequestParam("id") Long[] ids){
        System.out.println(ids);
        return productService.getProductsById(ids);
    }

}
