package org.computerShop.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.model.Category;
import org.computerShop.model.Views;
import org.computerShop.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
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
    @PostMapping(value = "/createCategory")
    @PreAuthorize("hasRole('ADMIN')")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonView(Views.CategoryFull.class)
    public ResponseEntity<String> createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }
    @DeleteMapping("/deleteCategory/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @JsonView(Views.CategoryFull.class)
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Category category){
        return categoryService.deleteCategory(category);
    }
    @PostMapping("/pushCategoryImage")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @PreAuthorize("hasRole('ADMIN')")
    public void saveCategoryImage(@RequestParam("file") MultipartFile files) throws IOException {
        categoryService.pushImage(files);
    }

    //TODO: EDIT
    @PutMapping("/editCategory")
    @PreAuthorize("hasRole('ADMIN')")
    @JsonView(Views.CategoryFull.class)
    public ResponseEntity<String> editCategory(@RequestBody Category category){
        return categoryService.editCategory(category);
    }
}
