package org.computerShop.service.impl;

import org.computerShop.model.User;
import org.computerShop.repository.UserRepo;
import org.computerShop.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    private UserRepo userRepo;

    @Autowired
    public ProfileServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public ResponseEntity<String> editUser(User user) {
        //User user1 = userRepo.findById(user.getId());
        if(user !=null && userRepo.existsByUsername(user.getUsername())){
            userRepo.save(user);
            return new ResponseEntity<String>("Ok",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Bad", HttpStatus.OK);
        }
    }



}
