package org.computerShop.service.impl;

import org.computerShop.model.ProductSection;
import org.computerShop.email.SendEmail;
import org.computerShop.model.*;
import org.computerShop.repository.*;
import org.computerShop.service.ProductService;
import org.computerShop.sockets.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {


    private SimpMessagingTemplate simpMessagingTemplate;
    @Value("${upload.path}")
    private String uploadPath;
    private List<Image> images;

    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;
    private ImageRepo imageRepo;
    private ProductSectionRepo productSectionRepo;
   //TODO: перенести
    private CpuRepo cpuRepo;

    private RemindMeRepo remindMeRepo;
    private SendEmail sendEmail;
    private SubscriptionRepo subscriptionRepo;


    private TemplateEngine templateEngine;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo,
                              ImageRepo imageRepo,
                              CategoryRepo categoryRepo,
                              CpuRepo cpuRepo,
                              RemindMeRepo remindMeRepo,
                              SendEmail sendEmail,
                              SimpMessagingTemplate simpMessagingTemplate,
                              SubscriptionRepo subscriptionRepo,
                              TemplateEngine templateEngine,
                              ProductSectionRepo productSectionRepo){
        this.productRepo = productRepo;
        this.imageRepo = imageRepo;
        this.categoryRepo = categoryRepo;
        this.cpuRepo = cpuRepo;
        this.remindMeRepo = remindMeRepo;
        this.sendEmail = sendEmail;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.subscriptionRepo = subscriptionRepo;
        this.templateEngine = templateEngine;
        this.productSectionRepo = productSectionRepo;
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
    public ResponseEntity<String> createProduct(Product product, boolean notifySubscribers) {
        List<Product> productsFromDb = productRepo.findAll();
        if (!productsFromDb.contains(product)) {
            if(notifySubscribers){
                subscriptionRepo.findAll().forEach(el->{
                    sendEmail.sendMail(el.getEmail(), "Now you can buy new " + product.getCategory().getCategoryName() + " in our shop","Already available " + product.getProductName() + " price: " + product.getProductPrice());
                });
            }
            productRepo.save(product);
            if (images != null){
                for (Image image : images){
                    image.setProduct(product);
                    imageRepo.save(image);
                }
            }
            product.getProperties().forEach((k,v)->{
                ProductSection productSection = new ProductSection();
                productSection.setTitle(k);
                productSection.setProduct(product);
                v.forEach((key,value)->{
                    productSection.getSections().put(key,value);
                });
                productSectionRepo.save(productSection);
            });

            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }
        return new ResponseEntity<>("Post already exist", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateProduct(Product product) {
        List<RemindMe> remindsMe = remindMeRepo.allForProduct(product.getId());
        if(remindsMe != null){
           product.setRemindMe(remindsMe);
        }
        productRepo.save(product);
        simpMessagingTemplate.convertAndSend("/topic/update", new ResponseMessage("updateProducts", true));
        if(product.getRemindMe() != null && product.getAmountInStock() != 0){
            for (RemindMe remindMe:product.getRemindMe()) {


                //sendEmail.sendMail(remindMe.getEmail(), "Remind", "Product: " + product.getProductName() + " is available");




            }
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Something wrong", HttpStatus.OK);
        }
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
            simpMessagingTemplate.convertAndSend("/topic/update", new ResponseMessage("updateProducts", true));
            return new ResponseEntity<>("Was deleted " + product.getProductName(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Product " + product.getProductName() + " not found", HttpStatus.OK);
    }

    @Override
    public Product detailsProduct(Product product){
        return product;
    }

    //TODO: MAX SIZE PHOTO 10 MB

    @Override
    public List<Product> filterByCategory(String categoryName){
        Category category = categoryRepo.findByCategoryName(categoryName);
        List<Product> products = productRepo.findAllByCategory(category);
        if (products.size() != 0){
            return products;
        }else {
            if(categoryName.contains("undefined") || categoryName.contains("all")) {
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
        }else if(brands != null && gpus != null){
            products.addAll(productRepo.findAllByBrandAndGpu(brands, gpus));
        }else if(cpus != null && gpus != null){
            products.addAll(productRepo.findAllByCpuAndGpu(cpus, gpus));
        }
        else{
            if (brands != null){
                products.addAll(productRepo.findAllByBrand(brands));
            }else if(cpus != null){
                products.addAll(productRepo.findAllByCpu(cpus));
            }else if(gpus != null){
                products.addAll(productRepo.findAllByGpu(gpus));
            }else{
                return productRepo.findAll();
            }
        }
        return products;
    }

    @Override
    public List<Product> mostPurchased() {
        List<Product> products = productRepo.findTop5ByOrderByNumberOfBuyDesc();
        System.out.println(products);
        return  productRepo.findTop5ByOrderByNumberOfBuyDesc();
    }

    @Override
    public ResponseEntity<String> setDiscountPrice(Product product, short percent, boolean notifySubscribers) {
        if(product != null){
            if(percent != 0) {
                int discountPrice = (int) Math.round(product.getProductPrice() - (product.getProductPrice() * (percent / 100.0)));
                product.setGlobalDiscount(true);
                product.setDiscountPrice(discountPrice);
                productRepo.save(product);
                if(notifySubscribers){
                    MailContentBuilder mailContentBuilder = new MailContentBuilder(templateEngine);
                    subscriptionRepo.findAll().forEach(el->{


                        sendEmail.sendMail(el.getEmail(),"Discount price for:", mailContentBuilder.createProductTemplate(product));



                    });
                }
                simpMessagingTemplate.convertAndSend("/topic/update", new ResponseMessage("updateProducts", true));
                return new ResponseEntity<>("Discount price has been set", HttpStatus.OK);
            }else {
                product.setGlobalDiscount(false);
                product.setDiscountPrice(0);
                productRepo.save(product);
                simpMessagingTemplate.convertAndSend("/topic/update", new ResponseMessage("updateProducts", true));
                return new ResponseEntity<>("Discount price has been removed", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Something wrong!", HttpStatus.OK);
    }

    @Override
    public List<Product> lastTenProducts() {
        return productRepo.findFirst8ByOrderByIdDesc();
    }

    @Override
    public List<Product> similarProducts(long productId, String categoryName) {
        System.out.println(productRepo.similarProducts(productId, categoryName));
        return productRepo.similarProducts(productId, categoryName);
    }


}

