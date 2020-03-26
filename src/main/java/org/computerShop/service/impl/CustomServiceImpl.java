package org.computerShop.service.impl;

import maps.Status;
import org.computerShop.email.SendEmail;
import org.computerShop.model.Custom;
import org.computerShop.model.enums.CustomStatus;
import org.computerShop.model.Item;
import org.computerShop.model.Product;
import org.computerShop.repository.CustomRepo;
import org.computerShop.repository.ProductRepo;
import org.computerShop.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
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


    private ArrayList<Status> allStatuses(){
        Status isBeingProcessed = new Status(CustomStatus.IS_BEING_PROCESSED, LocalDateTime.now(), 0);
        Status isCompleted = new Status(CustomStatus.IS_COMPLETED, 101);
        Status inTransit = new Status(CustomStatus.IN_TRANSIT, 102);
        Status atTheDestination = new Status(CustomStatus.AT_THE_DESTINATION, 103);
        Status received = new Status(CustomStatus.RECEIVED, 104);
        ArrayList<Status> statuses = new ArrayList<Status>(5){{
            add(isBeingProcessed);
            add(isCompleted);
            add(inTransit);
            add(atTheDestination);
            add(received);
        }};
        return statuses;
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
                        customToSave.setStatuses(allStatuses());
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
    public ResponseEntity<String> setStatus(Custom custom, int status) {
        boolean isSuccess = false;
        switch (status){
            case 0:
                break;
            case 1:
                custom.getStatuses().stream().filter(s->s.getStatusCode() == status).findFirst().orElse(new Status()).setStatusDateTime(null);
                custom.getStatuses().stream().filter(s->s.getStatusCode() == status).findFirst().orElse(new Status()).setStatusCode(101);
                isSuccess = true;
                break;
            case 2:
                custom.getStatuses().stream().filter(s->s.getStatusCode() == status).findFirst().orElse(new Status()).setStatusDateTime(null);
                custom.getStatuses().stream().filter(s->s.getStatusCode() == status).findFirst().orElse(new Status()).setStatusCode(102);
                isSuccess = true;
                break;
            case 3:
                custom.getStatuses().stream().filter(s->s.getStatusCode() == status).findFirst().orElse(new Status()).setStatusDateTime(null);
                custom.getStatuses().stream().filter(s->s.getStatusCode() == status).findFirst().orElse(new Status()).setStatusCode(103);
                isSuccess = true;
                break;
            case 4:
                custom.getStatuses().stream().filter(s->s.getStatusCode() == status).findFirst().orElse(new Status()).setStatusDateTime(null);
                custom.getStatuses().stream().filter(s->s.getStatusCode() == status).findFirst().orElse(new Status()).setStatusCode(104);
                isSuccess = true;
                break;
            case 100:
                break;
            case 101:
                custom.getStatuses().stream().filter(s->s.getStatusCode() == status).findFirst().orElse(new Status()).setStatusDateTime(LocalDateTime.now());
                custom.getStatuses().stream().filter(s->s.getStatusCode() == status).findFirst().orElse(new Status()).setStatusCode(1);
                isSuccess = true;
                break;
            case 102:
                custom.getStatuses().stream().filter(s->s.getStatusCode() == status).findFirst().orElse(new Status()).setStatusDateTime(LocalDateTime.now());
                custom.getStatuses().stream().filter(s->s.getStatusCode() == status).findFirst().orElse(new Status()).setStatusCode(2);
                isSuccess = true;
                break;
            case 103:
                custom.getStatuses().stream().filter(s->s.getStatusCode() == status).findFirst().orElse(new Status()).setStatusDateTime(LocalDateTime.now());
                custom.getStatuses().stream().filter(s->s.getStatusCode() == status).findFirst().orElse(new Status()).setStatusCode(3);
                isSuccess = true;
                break;
            case 104:
                custom.getStatuses().stream().filter(s->s.getStatusCode() == status).findFirst().orElse(new Status()).setStatusDateTime(LocalDateTime.now());
                custom.getStatuses().stream().filter(s->s.getStatusCode() == status).findFirst().orElse(new Status()).setStatusCode(4);
                isSuccess = true;
                break;
        }

        if(custom != null && isSuccess){
            customRepo.save(custom);
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("You can not set another status if Is being processed is false", HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> deleteCustom(Custom custom) {

        if(custom != null){
            customRepo.delete(custom);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Bad", HttpStatus.OK);
        }

    }
}
