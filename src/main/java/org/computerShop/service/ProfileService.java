package org.computerShop.service;

import org.computerShop.model.User;
import org.springframework.http.ResponseEntity;

public interface ProfileService {

    ResponseEntity<String> editUser(User user);


}
