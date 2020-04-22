package org.computerShop.service.impl;

import org.computerShop.model.RemindMe;
import org.computerShop.repository.RemindMeRepo;
import org.computerShop.service.RemindMeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RemindMeServiceImpl implements RemindMeService {

    private RemindMeRepo remindMeRepo;

    @Autowired
    public RemindMeServiceImpl(RemindMeRepo remindMeRepo){
        this.remindMeRepo = remindMeRepo;
    }

    @Override
    public ResponseEntity<String> createRemindMe(RemindMe remindMe) {

        if(remindMe != null){
            remindMeRepo.save(remindMe);
        }

        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}
