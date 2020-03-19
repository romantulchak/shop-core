package org.computerShop.service;

import org.computerShop.model.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {

    List<Category> getCategories();
    ResponseEntity<String> createCategory(Category category);
    ResponseEntity<String> deleteCategory(Category category);
    ResponseEntity<String> editCategory(Category category);

}
