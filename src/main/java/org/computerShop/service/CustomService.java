package org.computerShop.service;

import org.computerShop.model.Custom;
import org.computerShop.model.User;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomService {

    Custom getByIdentificationNumber(String identificationNumber);
    String createOrder(Custom custom);
    List<Custom> getAll();
    ResponseEntity<String> setStatus(Custom custom, int status);
    ResponseEntity<String> deleteCustom(Custom custom);
    ResponseEntity<String> setCancel(Custom custom);
    List<Custom> getAllForUser(User user);
    int checkAmount(String id, String amount);
    long getCountBuy();
    long getTotalBuyMoneyByDay();
    long getOrderCounterByDay();
}
