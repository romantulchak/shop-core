package org.computerShop.service;

import org.computerShop.model.PromotionalCode;
import org.springframework.http.ResponseEntity;

import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface PromotionalCodeService {

    ResponseEntity<String> createPromo(short percent, long numberOfDays, int numberOfUses, long productId) throws IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException;
    ResponseEntity<String> deletePromo(PromotionalCode promotionalCode);
    ResponseEntity<?> findCode(String code, long productId);

}
