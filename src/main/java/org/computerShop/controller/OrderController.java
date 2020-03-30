package org.computerShop.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.model.User;
import org.computerShop.model.Views;
import org.computerShop.service.CustomService;
import org.computerShop.service.impl.CustomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.computerShop.model.Custom;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("/api/order")
public class OrderController {

    private CustomServiceImpl customService;

    @Autowired
    public OrderController(CustomServiceImpl customService){
        this.customService = customService;
    }

    @PostMapping("/createOrder")
    public String createOrder(@RequestBody Custom custom){
        return customService.createOrder(custom);

    }
    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    @JsonView(Views.CustomFUll.class)
    public List<Custom> getCustoms(){
        return customService.getAll();
    }

    @GetMapping("/findByIdentificationNumber/{identificationNumber}")
    @JsonView(Views.CustomFUll.class)
    public List<Custom> findByIdentificationNumber(@PathVariable("identificationNumber") String identificationNumber){
        return customService.getAllByIdentificationNumber(identificationNumber);
    }


    @PutMapping("/setStatus")
    @PreAuthorize("hasRole('ADMIN')")
    @JsonView(Views.CustomFUll.class)
    public ResponseEntity<String> setCustomStatus(@RequestParam(value = "statusCode", required = false) String code, @RequestBody Custom custom){

        //TODO:FIX коли приходять не цифри
        return customService.setStatus(custom, Integer.parseInt(code));
    }

    @DeleteMapping("/deleteCustom/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCustom(@PathVariable("id") Custom custom){
        return customService.deleteCustom(custom);
    }

    @PutMapping("/cancel/{id}")
    @JsonView(Views.CustomUser.class)
    public ResponseEntity<String> cancel(@PathVariable("id") Custom custom){
        return customService.setCancel(custom);
    }

    @GetMapping("/getAllForUser/{id}")
    @JsonView(Views.UserFull.class)
    public List<Custom> getAllForUser(@PathVariable("id")User user){
        return customService.getAllForUser(user);
    }
}
