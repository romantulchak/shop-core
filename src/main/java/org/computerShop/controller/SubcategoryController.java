package org.computerShop.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.model.Subcategory;
import org.computerShop.model.Views;
import org.computerShop.service.impl.SubcategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping("/api/subcategory")
public class SubcategoryController {

    private SubcategoryServiceImpl subcategoryService;
    @Autowired
    public SubcategoryController(SubcategoryServiceImpl subcategoryService){
        this.subcategoryService = subcategoryService;
    }

    @GetMapping("/getSubcategories")
    @JsonView({Views.SubcategoryFull.class})
    public List<Subcategory> getSubcategories(){
        return subcategoryService.getSubcategories();
    }
    @PostMapping("/createSubcategory")
    public ResponseEntity<?> createSubcategory(@RequestBody Subcategory subcategory){
        return subcategoryService.createSubcategory(subcategory);
    }

    @JsonView(Views.SubcategoryFull.class)
    @GetMapping("/getSubcategoriesByCategory/{categoryName}")
    public List<Subcategory> getSubcategoriesByCategory(@PathVariable("categoryName") String categoryName){
        return subcategoryService.getSubcategoriesByCategory(categoryName);
    }



}
