package org.computerShop.controller;

import org.computerShop.model.RemindMe;
import org.computerShop.service.impl.RemindMeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/remindMe")
public class RemindMeController {

    private RemindMeServiceImpl remindMeService;

    @Autowired
    public RemindMeController(RemindMeServiceImpl remindMeService){
        this.remindMeService = remindMeService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createRemindMe(@RequestBody RemindMe remindMe){
        return remindMeService.createRemindMe(remindMe);
    }




}
