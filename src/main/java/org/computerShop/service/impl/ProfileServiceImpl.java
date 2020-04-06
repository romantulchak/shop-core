package org.computerShop.service.impl;

import org.computerShop.dto.UserDto;
import org.computerShop.model.Role;
import org.computerShop.model.User;
import org.computerShop.repository.UserRepo;
import org.computerShop.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    private UserRepo userRepo;

    @Autowired
    public ProfileServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public ResponseEntity<String> editUser(UserDto user){


        User userToSave = userRepo.findById(user.getId()).orElse(null);

       //userRepo.save(user);
   // return new ResponseEntity<>("sad", HttpStatus.OK);

 if (userToSave != null){
            userToSave.setFirstName(user.getFirstName());
            userToSave.setLastName(user.getLastName());
            userToSave.setCity(user.getCity());
            userToSave.setAddress(user.getAddress());
            userToSave.setPostalCode(user.getPostalCode());
            userToSave.setMobilePhone(user.getMobilePhone());
            userRepo.save(userToSave);
            return new ResponseEntity<String>("Ok",HttpStatus.OK);

        }else {
            return new ResponseEntity<>("Bad", HttpStatus.OK);
        }

    }

    @Override
    public UserDto getCurrentUser(User user) {
        if (user != null){
            List<String> userRoles = new ArrayList<>();
            user.getRoles().forEach(x->{
                userRoles.add(x.getName().toString());
            });


            return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getCity(), user.getAddress(), user.getPostalCode(),user.getMobilePhone(),userRoles);
        }


        return null;
    }
}
