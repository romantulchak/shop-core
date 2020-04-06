package org.computerShop.service;

import org.computerShop.dto.UserDto;
import org.computerShop.model.User;
import org.springframework.http.ResponseEntity;

public interface ProfileService {

    ResponseEntity<String> editUser(UserDto user);
    UserDto getCurrentUser(User user);

}
