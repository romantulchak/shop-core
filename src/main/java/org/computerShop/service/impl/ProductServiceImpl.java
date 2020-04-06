package org.computerShop.service.impl;

import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.model.*;
import org.computerShop.model.accessory.CPU;
import org.computerShop.repository.CategoryRepo;
import org.computerShop.repository.CpuRepo;
import org.computerShop.repository.ImageRepo;
import org.computerShop.repository.ProductRepo;
import org.computerShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
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
    private CpuRepo cpuRepo;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo, ImageRepo imageRepo, CategoryRepo categoryRepo, CpuRepo cpuRepo){
        this.productRepo = productRepo;
        this.imageRepo = imageRepo;
        this.categoryRepo = categoryRepo;
        this.cpuRepo = cpuRepo;
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

    //TODO: MAX SIZE PHOTO 10 MB

    @Override
    public List<Product> filterByCategory(String categoryName){
        Category category = categoryRepo.findByCategoryName(categoryName);
        List<Product> products = productRepo.findAllByCategory(category);
        if (products.size() != 0){



            return products;
        }else {

            if(categoryName.contains("undefined")) {
                return productRepo.findAll();
            }else{
                return products;
            }
        }

    }

    @Override
    public List<Product> filter(String[] brands, String[] cpus, String[] gpus) {
        List<Product> products = new ArrayList<>();

        if(brands != null && cpus != null && gpus != null){
            products.addAll(productRepo.findAllByBrandAndCpuAndGpu(brands, cpus, gpus));
        }else if(brands != null && cpus != null){
            products.addAll(productRepo.findAllByBrandAndCpu(brands,cpus));
        }else{
            if (brands != null){
                products.addAll(productRepo.findAllByBrand(brands));
            }else if(cpus != null){
                products.addAll(productRepo.findAllByCpu(cpus));
            }else{
                return productRepo.findAll();
            }
        }
        return products;
    }



}

