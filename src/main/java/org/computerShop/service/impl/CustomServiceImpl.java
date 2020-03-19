package org.computerShop.service.impl;

import org.computerShop.model.Custom;
import org.computerShop.model.Item;
import org.computerShop.model.Product;
import org.computerShop.repository.CustomRepo;
import org.computerShop.repository.ProductRepo;
import org.computerShop.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        if(custom != null){

            if(custom.getItems().size() != 0 && custom.getItems() != null){

                for(Item item: custom.getItems()) {
                    Custom customToSave = new Custom();
                    Product product = productRepo.findById(item.getId()).orElse(null);
                    if (product != null) {

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
                        customRepo.save(customToSave);
                    }
                }
            }

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
}
