package org.computerShop.service;

import org.computerShop.model.Subscription;
import org.computerShop.model.User;
import org.springframework.http.ResponseEntity;

public interface SubscriptionService {

    ResponseEntity<String> createSubscription(Subscription subscription, User user);


}
