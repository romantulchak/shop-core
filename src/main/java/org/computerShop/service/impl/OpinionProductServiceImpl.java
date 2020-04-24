package org.computerShop.service.impl;

import org.computerShop.dto.OpinionsDto;
import org.computerShop.model.OpinionProduct;
import org.computerShop.model.User;
import org.computerShop.sockets.OpinionMessage;
import org.computerShop.repository.OpinionProductRepo;
import org.computerShop.repository.UserRepo;
import org.computerShop.service.OpinionProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OpinionProductServiceImpl implements OpinionProductService {

    private OpinionProductRepo opinionProductRepo;
    private UserRepo userRepo;
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public OpinionProductServiceImpl(OpinionProductRepo opinionProductRepo, UserRepo userRepo, SimpMessagingTemplate simpMessagingTemplate){
        this.opinionProductRepo = opinionProductRepo;
        this.userRepo = userRepo;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }


    @Override
    public ResponseEntity<?> createOpinion(OpinionProduct opinionProduct, long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if(user != null){
            opinionProduct.setUser(user);
            opinionProduct.setDateTime(LocalDateTime.now());
            opinionProductRepo.save(opinionProduct);
            simpMessagingTemplate.convertAndSend("/topic/update", new OpinionMessage("updateOpinion", opinionProduct.getCommentToProduct().getId()));
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something wrong", HttpStatus.OK);
    }

    @Override
    public double getAverageRanking(long productId) {

        double average = opinionProductRepo.average(productId);
        BigDecimal bd = new BigDecimal(Double.toString(average));
        bd = bd.setScale(1, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
/*
    @Override
    public List<OpinionProduct> getOpinionForProduct(long productId) {
        return opinionProductRepo.findAllForProduct(productId);
    }

 */

    @Override
    public OpinionsDto getOpinionForProduct(long productId, int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<OpinionProduct> products = opinionProductRepo.findAllForProduct(productId, pageable);
        System.out.println("SIZE: " + products.toList().size());
        return new OpinionsDto(products.toList(), pageable.getPageNumber(), products.getTotalPages(), opinionProductRepo.findAllForProduct(productId).size());
    }
}
