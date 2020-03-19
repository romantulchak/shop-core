package org.computerShop.service.impl;

import org.computerShop.model.Category;
import org.computerShop.repository.CategoryRepo;
import org.computerShop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepo categoryRepo;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo){
        this.categoryRepo = categoryRepo;
    }


    @Override
    public List<Category> getCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public ResponseEntity<String> createCategory(Category category){
        List<Category> categoriesFromDb = categoryRepo.findAll();

         if (!categoriesFromDb.contains(category) && !category.getCategoryName().isEmpty()){
            categoryRepo.save(category);
            return new ResponseEntity<>("Was created " + category.getCategoryName(), HttpStatus.OK);
        }else {

            return new ResponseEntity<>("Bad", HttpStatus.OK);
        }


    }


    @Override
    public ResponseEntity<String> deleteCategory(Category category){
        if (category !=null){

            categoryRepo.delete(category);

            return new ResponseEntity<>("Ok category", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Bad delete category", HttpStatus.OK);
        }
    }
    @Override
    public ResponseEntity<String> editCategory(Category category){

        if (category!=null) {
            categoryRepo.save(category);
        }
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}
