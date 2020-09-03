package org.computerShop.service;

import org.computerShop.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CategoryService {

    List<Category> getCategories();
    ResponseEntity<String> createCategory(Category category);
    ResponseEntity<String> deleteCategory(Category category);
    ResponseEntity<String> editCategory(Category category);
    void pushImage(MultipartFile multipartFile) throws IOException;
}
