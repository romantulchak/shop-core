package org.computerShop.service.impl;

import org.computerShop.model.Subscription;
import org.computerShop.model.User;
import org.computerShop.repository.SubscriptionRepo;
import org.computerShop.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {


    private SubscriptionRepo subscriptionRepo;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepo subscriptionRepo){
        this.subscriptionRepo = subscriptionRepo;
    }

    @Override
    public ResponseEntity<String> createSubscription(Subscription subscription, User user) {
        if(subscription != null) {
            if (!subscriptionRepo.existsByEmail(subscription.getEmail())){
                if(user != null){
                    subscription.setUser(user);
                }
                subscriptionRepo.save(subscription);
                return new ResponseEntity<>("Ok", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Email already in system", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Something wrong", HttpStatus.OK);
    }
}
