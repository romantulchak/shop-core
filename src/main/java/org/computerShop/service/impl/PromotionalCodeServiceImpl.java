package org.computerShop.service.impl;

import org.computerShop.model.Product;
import org.computerShop.model.PromotionalCode;
import org.computerShop.repository.ProductRepo;
import org.computerShop.repository.PromotionalCodeRepo;
import org.computerShop.service.PromotionalCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class PromotionalCodeServiceImpl implements PromotionalCodeService {

    private PromotionalCodeRepo promotionalCodeRepo;
    private ProductRepo productRepo;
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    public PromotionalCodeServiceImpl(PromotionalCodeRepo promotionalCodeRepo, ProductRepo productRepo, SimpMessagingTemplate simpMessagingTemplate){
        this.promotionalCodeRepo = promotionalCodeRepo;
        this.productRepo = productRepo;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }


    @Override
    public ResponseEntity<String> createPromo(short percent, long numberOfDays, int numberOfUses, long productId) {
        String generatedCode =generateRandomCode();
        Product product = productRepo.findById(productId).orElse(null);
        PromotionalCode promotionalCode = new PromotionalCode(LocalDateTime.now(), LocalDateTime.now().plusDays(numberOfDays), numberOfUses, percent, generatedCode, product);
        promotionalCodeRepo.save(promotionalCode);
        return new ResponseEntity<>(generatedCode, HttpStatus.OK);
    }


    private String generateRandomCode(){
        short codeLength = 8;

        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toUpperCase().toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new SecureRandom();
        for(int i = 0; i < codeLength; i++ ){
            char c = chars[random.nextInt(chars.length)];
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

/*
    //TODO: віднімати юзабіліті
    @Override
    public Number findCode(String code, long productId) {
        Product product = productRepo.findById(productId).orElse(null);
        PromotionalCode promotionalCode = promotionalCodeRepo.findByCode(code);
        if(promotionalCode != null){


            return promotionalCode.getPercent();
        }else {
            return null;
        }
    }
*/
    @Override
    public ResponseEntity<?> findCode(String code, long productId) {
        Product product = productRepo.findById(productId).orElse(null);
        PromotionalCode promotionalCode = promotionalCodeRepo.findByCode(code);
        if(promotionalCode != null){

            if(promotionalCode.getProduct().equals(product)) {
                if(promotionalCode.getExpirationDate().isAfter(LocalDateTime.now()) && promotionalCode.getNumberOfUses() != 0) {
                    promotionalCode.setNumberOfUses(promotionalCode.getNumberOfUses() - 1);
                    promotionalCodeRepo.save(promotionalCode);
                    simpMessagingTemplate.convertAndSend("/topic/update", "update");
                    return new ResponseEntity<Integer>(promotionalCode.getPercent(), HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("EXPIRED", HttpStatus.OK);
                }
            }else{
                return new ResponseEntity<>("UNCORRECTED_PRODUCT" , HttpStatus.OK);
            }
        }else {
            return null;
        }
    }

    @Override
    public ResponseEntity<String> deletePromo(PromotionalCode promotionalCode) {
        return null;
    }


}
