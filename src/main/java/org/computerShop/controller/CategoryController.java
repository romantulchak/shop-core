package org.computerShop.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.model.Category;
import org.computerShop.model.Views;
import org.computerShop.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryServiceImpl categoryService;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    @JsonView(Views.CategoryFull.class)
    public List<Category> getCategories(){
        return this.categoryService.getCategories();
    }


    @PostMapping("/createCategory")
    @JsonView(Views.CategoryFull.class)
    public ResponseEntity<String> createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }
    @DeleteMapping("/deleteCategory/{id}")
    @JsonView(Views.CategoryFull.class)
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Category category){
        return categoryService.deleteCategory(category);
    }

    @PutMapping("/editCategory")
    @JsonView(Views.CategoryFull.class)
    public ResponseEntity<String> editCategory(@RequestBody Category category){
        return categoryService.editCategory(category);


    }
}
