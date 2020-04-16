package org.computerShop.service.impl;

import org.computerShop.maps.Status;
import org.computerShop.email.SendEmail;
import org.computerShop.model.*;
import org.computerShop.model.enums.CustomStatus;
import org.computerShop.repository.CustomProductRepo;
import org.computerShop.repository.CustomRepo;
import org.computerShop.repository.ProductRepo;
import org.computerShop.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CustomServiceImpl implements CustomService {

    private CustomRepo customRepo;
    private ProductRepo productRepo;
    private CustomProductRepo customProductRepo;
    private SendEmail sendEmail;
    @Autowired
    public CustomServiceImpl(CustomRepo customRepo,
                             ProductRepo productRepo,
                             CustomProductRepo customProductRepo,
                             SendEmail sendEmail){
        this.customRepo = customRepo;
        this.productRepo = productRepo;
        this.customProductRepo = customProductRepo;
        this.sendEmail = sendEmail;
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

    //TODO: FIX IT
    @Override
    public String createOrder(Custom custom) {
        String identificationNumber = generateRandomStringNumber();
        ArrayList<Product> products = new ArrayList<>();
        custom.setCreatedDate(LocalDateTime.now());
        custom.setIdentificationNumber(identificationNumber);
        custom.setStatuses(allStatuses());
        custom.setAmount(custom.getItems().size());
        customRepo.save(custom);
        custom.getItems().forEach(item-> {
            Product product = productRepo.findById(item.getId()).orElse(null);
            if (product != null) {
                product.setAmountInStock(product.getAmountInStock() - item.getAmount());
                product.setNumberOfBuy(product.getNumberOfBuy() + item.getAmount());
                CustomProduct customProduct = new CustomProduct(custom, product, item.getAmount(), product.getProductPrice());
                productRepo.save(product);
                customProductRepo.save(customProduct);
            }
        });


        if (custom.getEmail() != null) {
            sendEmail.sendMail(custom.getEmail(), "Your order number", contentToSend(products, custom, identificationNumber).toString());
        }
        return identificationNumber;
    }


    private StringBuilder contentToSend(ArrayList<Product> products, Custom custom, String identificationNumber){
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
        return stringBuilder;
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


    //TODO: враховувати Cancel
    @Override
    public Custom getByIdentificationNumber(String identificationNumber) {
        return customRepo.findFirstByIdentificationNumber(identificationNumber);
    }


    //TODO: fix it
    @Override
    public List<Custom> getAll(){
        return customRepo.findAllByOrderByIdDesc();
    }

    //TODO: баг з датою якщо забрати виконання

    @Override
    public ResponseEntity<String> setStatus(Custom custom, int status) {

        List<Custom> customs = customRepo.findAllByIdentificationNumber(custom.getIdentificationNumber());
        boolean isSuccess = false;
        switch (status){
            case 0:
                //TODO: пофіксити
                break;
            case 1:

                changeStatus(1, customs, 101);
                changeStatus(2, customs, 102);
                changeStatus(3, customs, 103);
                changeStatus(4, customs, 104);
                isSuccess = true;
                break;
            case 2:
                changeStatus(2, customs, 102);
                changeStatus(3, customs, 103);
                changeStatus(4, customs, 104);
                isSuccess = true;
                break;
            case 3:

                changeStatus(3, customs, 103);
                changeStatus(4, customs, 104);
                isSuccess = true;
                break;
            case 4:
                changeStatus(4, customs, 104);
                isSuccess = true;
                break;
            case 100:
                break;
            case 101:
                changeStatus(101, customs, 1);
                isSuccess = true;
                break;
            case 102:
                changeStatus(102, customs, 2);
                isSuccess = true;
                break;
            case 103:
                changeStatus(103, customs, 3);
                isSuccess = true;
                break;
            case 104:
                changeStatus(104, customs, 4);
                isSuccess = true;
                break;
        }

        if(customs != null && isSuccess){
            customRepo.saveAll(customs);
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("You can not set another status if Is being processed is false", HttpStatus.OK);
        }
    }


    private void changeStatus(int status, List<Custom> customs, int statusToSet){


        customs.forEach(s->{
            s.getStatuses().stream().filter(x->x.getStatusCode() == status)
                    .findFirst().orElse(new Status()).setStatusDateTime(LocalDateTime.now());
            s.getStatuses().stream().filter(x->x.getStatusCode() == status).
                    findFirst()
                    .orElse(new Status())
                    .setStatusCode(statusToSet);

        });

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


    @Override
    public ResponseEntity<String> setCancel(Custom custom){
        List<Custom> customs = customRepo.findAllByIdentificationNumber(custom.getIdentificationNumber());
        if(customs != null){
            customs.forEach(x->{
                x.setCancel(!x.isCancel());
                customRepo.save(x);
            });
            return new ResponseEntity<>("Good", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Bad", HttpStatus.OK);
        }
    }

    @Override
    public List<Custom> getAllForUser(User user) {

        Collections.reverse(user.getCustom());
        return  user.getCustom();
    }

    @Override
    public int checkAmount(String id, String amount) {
        int amountToReturn = Integer.parseInt(amount);
        Product product = productRepo.findById(Long.parseLong(id)).orElse(null);
        if(product != null){
            if(product.getAmountInStock() > amountToReturn){
                return amountToReturn;
            }else{
                return product.getAmountInStock();
            }
        }else {
            return amountToReturn;
        }
    }
}
