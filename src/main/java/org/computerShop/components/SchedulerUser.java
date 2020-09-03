package org.computerShop.components;

import org.computerShop.model.User;
import org.computerShop.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SchedulerUser {

    private final UserRepo userRepo;

    @Autowired
    public SchedulerUser(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void changeNewUser(){
        Set<User> users = userRepo.findAllUsersByStatus();
        users.stream().map(this::setNewUserStatus).collect(Collectors.toList());
        userRepo.saveAll(users);
    }
    public User setNewUserStatus(User user){
        user.setNew(false);
        return user;
    }
}
