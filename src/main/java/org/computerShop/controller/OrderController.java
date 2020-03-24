package org.computerShop.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.model.Views;
import org.computerShop.service.CustomService;
import org.computerShop.service.impl.CustomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("getAll")
    @JsonView(Views.CustomFUll.class)
    public List<Custom> getCustoms(){
        return customService.getAll();
    }

    @GetMapping("/findByIdentificationNumber/{identificationNumber}")
    @JsonView(Views.CustomFUll.class)
    public List<Custom> findByIdentificationNumber(@PathVariable("identificationNumber") String identificationNumber){
        return customService.getAllByIdentificationNumber(identificationNumber);
    }
    @PutMapping("/setIsBeingProcessed")
    @JsonView(Views.CustomFUll.class)
    public List<Custom> setIsBeingProcessed(@RequestBody Custom custom){
        return customService.setStatus(custom);
    }
    @PutMapping("/setCompleted")
    @JsonView(Views.CustomFUll.class)
    public List<Custom> setCompleted(@RequestBody Custom custom){
        return customService.setStatus(custom);
    }

}
