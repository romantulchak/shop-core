package org.computerShop.service.impl;

import org.computerShop.dto.CommentsDto;
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
import java.util.Set;

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
            OpinionProduct opinionProductToSave = new OpinionProduct(opinionProduct.getCommentToProduct(), user, opinionProduct.getText(), LocalDateTime.now(), opinionProduct.getRating(), opinionProduct.getAdvantages(), opinionProduct.getDisadvantages());
            opinionProductRepo.save(opinionProductToSave);
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


    @Override
    public OpinionsDto getOpinionForProduct(long productId, int page, User user) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<CommentsDto> products = null;

          products = opinionProductRepo.findAllForProduct(productId,pageable,user);
             return new OpinionsDto(products.toList(), pageable.getPageNumber(), products.getTotalPages(), opinionProductRepo.findAllForProduct(productId).size());
    }

    @Override
    public ResponseEntity<String> setLike(User user, OpinionProduct opinionProduct) {
        Set<User> likes = opinionProduct.getLikes();
        if(likes.contains(user)){
            likes.remove(user);
        }else{
            likes.add(user);
        }
        opinionProductRepo.save(opinionProduct);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}
