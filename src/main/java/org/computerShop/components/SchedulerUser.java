package org.computerShop.components;

import org.computerShop.model.Income;
import org.computerShop.model.User;
import org.computerShop.repository.CustomRepo;
import org.computerShop.repository.IncomeRepo;
import org.computerShop.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SchedulerUser {

    private final UserRepo userRepo;
    private final IncomeRepo incomeRepo;
    private final CustomRepo customRepo;
    @Autowired
    public SchedulerUser(UserRepo userRepo, IncomeRepo incomeRepo, CustomRepo customRepo){
        this.userRepo = userRepo;
        this.incomeRepo = incomeRepo;
        this.customRepo = customRepo;
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

    @Scheduled(cron = "0 0 0 * * ?")
    public void saveIncome(){
        incomeRepo.save(new Income(customRepo.getTotalCustomPriceByMonth()));
    }
}
