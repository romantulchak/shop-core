package org.computerShop.service.impl;

import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.model.Category;
import org.computerShop.model.Image;
import org.computerShop.model.Product;
import org.computerShop.model.Views;
import org.computerShop.repository.CategoryRepo;
import org.computerShop.repository.ImageRepo;
import org.computerShop.repository.ProductRepo;
import org.computerShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Value("${upload.path}")
    private String uploadPath;
    private List<Image> images;

    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;
    private ImageRepo imageRepo;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo, ImageRepo imageRepo, CategoryRepo categoryRepo){
        this.productRepo = productRepo;
        this.imageRepo = imageRepo;
        this.categoryRepo = categoryRepo;
    }



    @Override
    public List<Product> allProducts() {
        List<Product> products = productRepo.findAll();
        return productRepo.findAll();
    }

    @Override
    public List<Product> filterByPrice() {
        return productRepo.getAllByOrderByProductPrice();
    }

    @Override
    public ResponseEntity<String> createProduct(Product product) {
        System.out.println(images);
        //product.getImage().addAll(images);
        List<Product> productsFromDb = productRepo.findAll();
        if (!productsFromDb.contains(product)) {
            productRepo.save(product);

            if (images != null){
                for (Image image : images){
                    image.setProduct(product);
                    imageRepo.save(image);
                }
            }
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }
        return new ResponseEntity<>("Post already exist", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> editProduct(Product product) {
        return null;
    }

    //TODO: пофіксити видалення
    @Override
    public HttpStatus pushImage(MultipartFile[] files) throws IOException {
        images = new ArrayList<>();
        if (files != null){

            for (MultipartFile file: files){
                if (file != null && !file.getOriginalFilename().isEmpty()){
                    File uploadDir = new File(uploadPath);
                    if(!uploadDir.exists()){
                        uploadDir.mkdir();
                    }
                    String uuidFile = UUID.randomUUID().toString();
                    String resultFullName = uuidFile + "." + file.getOriginalFilename();
                    file.transferTo(new File(uploadPath + "/" + resultFullName));
                    String imagePath = "http://localhost:8080/productImages/" + resultFullName;
                    Image image = new Image(imagePath);
                    images.add(image);
                }

            }


        }

        return HttpStatus.OK;
    }

    @Override
    public ResponseEntity<String> deleteProduct(Product product) {

        Product productFromDb = productRepo.findById(product.getId()).orElse(null);
        if (productFromDb != null){
            productRepo.delete(product);
            return new ResponseEntity<>("Was deleted " + product.getProductName(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Product " + product.getProductName() + " not found", HttpStatus.OK);
    }

    @Override
    public Product detailsProduct(Product product){
        if (product != null){
            return product;
        }else {
            return null;
        }
    }

    @Override

    public List<Product> filterByCategory(String categoryName){
        Category category = categoryRepo.findByCategoryName(categoryName);
        List<Product> products = productRepo.findAllByCategory(category);
        if (products.size() != 0){
            return products;
        }else {
            return productRepo.findAll();
        }

    }

    @Override
    public List<Product> getProductsById(Long[] ids) {
        List<Product> products = new ArrayList<>();
        for (Long id: ids){
            Product product = productRepo.findById(id).orElse(null);
            if(product != null){
                products.add(product);
            }
        }
        return products;
    }
}
