package org.computerShop.service;

import org.computerShop.model.Subcategory;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubcategoryService {
    ResponseEntity<?> createSubcategory(Subcategory subcategory);
    List<Subcategory> getSubcategories();
    List<Subcategory> getSubcategoriesByCategory(String categoryName);

}
