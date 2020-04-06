package org.computerShop.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.dto.UserDto;
import org.computerShop.model.User;
import org.computerShop.model.Views;
import org.computerShop.service.impl.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/profile")
public class ProfileController {


    private ProfileServiceImpl profileService;
    @Autowired
    public ProfileController(ProfileServiceImpl profileService){
        this.profileService = profileService;
    }


    @GetMapping("/userDetails/{id}")
    public UserDto currentUser(@PathVariable("id") User user){
        return profileService.getCurrentUser(user);
    }

    @PutMapping("/editUser")
    @JsonView(Views.UserFull.class)
    public ResponseEntity<String> editUser(@RequestBody UserDto user){
        return profileService.editUser(user);
    }









    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
