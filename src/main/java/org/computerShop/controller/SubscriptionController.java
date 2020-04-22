package org.computerShop.controller;

import org.computerShop.model.Subscription;
import org.computerShop.model.User;
import org.computerShop.service.impl.SubscriptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping("/api/subscription")
public class SubscriptionController {

    private SubscriptionServiceImpl subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionServiceImpl subscriptionService){
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> follow(@RequestBody Subscription subscription, @PathVariable("id") User user){
        return this.subscriptionService.createSubscription(subscription, user);
    }
}
