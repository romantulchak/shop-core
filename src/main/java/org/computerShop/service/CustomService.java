package org.computerShop.service;

import org.computerShop.model.Custom;

import java.util.List;

public interface CustomService {

    List<Custom> getAllByIdentificationNumber(String identificationNumber);
    String createOrder(Custom custom);


}
