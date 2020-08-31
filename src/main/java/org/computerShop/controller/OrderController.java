package org.computerShop.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.model.User;
import org.computerShop.model.Views;
import org.computerShop.service.impl.CustomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.computerShop.model.Custom;

import java.util.List;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
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
    public Custom findByIdentificationNumber(@PathVariable("identificationNumber") String identificationNumber){
        return customService.getByIdentificationNumber(identificationNumber);
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

    @GetMapping("/checkAmount")
    public int checkAmount(@RequestParam(value="productId", required = false) String id, @RequestParam(value = "productAmount", required = false) String amount){
        return customService.checkAmount(id, amount);
    }

    @GetMapping("/countBuy")
    @PreAuthorize("hasRole('ADMIN')")
    public long getCountBuy(){
        return customService.getCountBuy();
    }

    @GetMapping("/totalMoneyByDay")
    @PreAuthorize("hasRole('ADMIN')")
    public long getTotalMoneyByDay(){
        return customService.getTotalBuyMoneyByDay();
    }
}
