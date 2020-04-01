package org.computerShop.controller;


import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.model.Brand;
import org.computerShop.model.Views;
import org.computerShop.service.impl.BrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/brands")
public class BrandController {

    private BrandServiceImpl brandService;

    @Autowired
    public BrandController(BrandServiceImpl brandService){
        this.brandService = brandService;
    }


    @GetMapping
    @JsonView(Views.ProductFull.class)
    public List<Brand> getAllBrands(){
        return brandService.getAll();
    }

    @PostMapping("/createBrand")
    public ResponseEntity<String> createBrand(@RequestBody Brand brand){

        return brandService.createBrand(brand);
    }
}
