package org.computerShop.service.impl;

import org.computerShop.dto.CommentsDto;
import org.computerShop.dto.OpinionsDto;
import org.computerShop.model.OpinionProduct;
import org.computerShop.model.User;
import org.computerShop.repository.OpinionProductRepo;
import org.computerShop.repository.UserRepo;
import org.computerShop.service.OpinionProductService;
import org.computerShop.sockets.OpinionMessage;
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
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

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
        Page<OpinionProduct> products = opinionProductRepo.findAllForProduct(productId,pageable);;
        products.forEach(el->{
            if(isContains(user, el.getLikes())){
                el.setMeLiked(true);
            }else if(isContains(user, el.getDislikes())){
                el.setMeDisliked(true);
            }
        });
        return new OpinionsDto(products.toList(), pageable.getPageNumber(), products.getTotalPages(), opinionProductRepo.findAllForProduct(productId).size());
    }




    private boolean isContains(User user, Set<User> likes) {
        return likes.contains(user);
    }

    @Override
    public ResponseEntity<String> setLike(User user, OpinionProduct opinionProduct) {
        Set<User> likes = opinionProduct.getLikes();
        Set<User> dislikes = opinionProduct.getDislikes();
        return getStringResponseEntity(user, opinionProduct, likes, dislikes);
    }

    private ResponseEntity<String> getStringResponseEntity(User user, OpinionProduct opinionProduct, Set<User> likes, Set<User> dislikes) {
        if(isContains(user, dislikes)){
            dislikes.remove(user);
        }
        if(isContains(user, likes)){
            likes.remove(user);
        }else{
            likes.add(user);
        }
        opinionProductRepo.save(opinionProduct);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }


    @Override
    public ResponseEntity<String> setDislike(User user, OpinionProduct opinionProduct) {
        Set<User> dislikes = opinionProduct.getDislikes();
        Set<User> likes = opinionProduct.getLikes();
        return getStringResponseEntity(user, opinionProduct, dislikes, likes);
    }
}
