package org.computerShop.service.impl;

import org.computerShop.email.SendEmail;
import org.computerShop.model.Custom;
import org.computerShop.model.Item;
import org.computerShop.model.Product;
import org.computerShop.repository.CustomRepo;
import org.computerShop.repository.ProductRepo;
import org.computerShop.service.CustomService;
import org.hibernate.sql.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomServiceImpl implements CustomService {

    private CustomRepo customRepo;
    private ProductRepo productRepo;
    @Autowired
    public CustomServiceImpl(CustomRepo customRepo, ProductRepo productRepo){
        this.customRepo = customRepo;
        this.productRepo = productRepo;
    }


    @Override
    public String createOrder(Custom custom) {
        String identificationNumber = generateRandomStringNumber();
        List<Product> products = new ArrayList<>();
        if(custom != null){

            if(custom.getItems().size() != 0 && custom.getItems() != null){

                for(Item item: custom.getItems()) {
                    Custom customToSave = new Custom();
                    Product product = productRepo.findById(item.getId()).orElse(null);
                    if (product != null) {
                        products.add(product);
                        customToSave.setAmount(item.getAmount());
                        customToSave.setProduct(product);
                        customToSave.setCreatedDate(LocalDateTime.now());
                        customToSave.setIdentificationNumber(identificationNumber);
                        customToSave.setCostumerAddress(custom.getCostumerAddress());
                        customToSave.setCostumerCity(custom.getCostumerCity());
                        customToSave.setCostumerLastName(custom.getCostumerLastName());
                        customToSave.setCostumerName(custom.getCostumerName());
                        customToSave.setCustomerMobilePhone(custom.getCustomerMobilePhone());
                        customToSave.setCustomerPostalCode(custom.getCustomerPostalCode());
                        customToSave.setTotalPrice(custom.getTotalPrice());
                        customToSave.setEmail(custom.getEmail());
                        customRepo.save(customToSave);
                    }
                }
            }

        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<div style=\"text-align: center; justify-content: center; align-items: center; height: 500px; display:flex;\">")
                .append("<div style=\" width: 100%; \">")
                .append("<h1>").append("Dear, ").append(custom.getCostumerName()).append(" ").append(custom.getCostumerLastName()).append("</h1>")
                .append("<h2 style=\"color: green; font-family:sans-serif;\">").append("Your order number: ").append(identificationNumber)
                .append("</h2>")
                .append("<h3>").append("Location: ").append(custom.getCostumerCity()).append(" ").append(custom.getCostumerAddress()).append("</h3>")
                .append("<h3>").append("Total price: ").append(custom.getTotalPrice()).append("</h3>");
        for (Product product: products) {

            stringBuilder.append("<div class=\"product\">").append("<h3>").append("<a href=\" " ).append("http://localhost:4200/detailsProduct/")
                    .append(product.getId()).append("\"").append("</a>")
                    .append(product.getProductName()).append("</h3>").append("</div>");

        }
        stringBuilder.append("</div>").append("</div>");

        SendEmail email = new SendEmail();
        if (custom.getEmail() != null) {
            email.sendMail(custom.getEmail(), "Your order number", stringBuilder.toString());
        }
        return identificationNumber;
    }


    private String generateRandomStringNumber(){
        String numericString = "0123456789";
        short amount = 20;
        StringBuilder stringBuilder = new StringBuilder(amount);
        for(int i = 0; i < amount; i++){
            int index = (int)(numericString.length() * Math.random());
            stringBuilder.append(numericString.charAt(index));
        }
        return stringBuilder.toString();

    }


    @Override
    public List<Custom> getAllByIdentificationNumber(String identificationNumber) {
        List<Custom> customs = customRepo.findAllByIdentificationNumber(identificationNumber);
        System.out.println(customs);
        return customRepo.findAllByIdentificationNumber(identificationNumber);
    }
    @Override
    public List<Custom> getAll(){


        List<Custom> sdsa = new ArrayList<>();
        List<String> strings = customRepo.getUnique();
        List<Custom> customsToShow = customRepo.findAll();

        for (String ss: strings) {

            Custom c = customRepo.getFirstByIdentificationNumber(ss);





            sdsa.add(c);

        }






        return sdsa;
    }

    @Override
    public List<Custom> setStatus(Custom custom){
        if(custom != null){
            customRepo.save(custom);
        }
        return getAll();
    }



}
