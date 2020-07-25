package org.computerShop.repository;

import org.computerShop.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubcategoryRepo extends JpaRepository<Subcategory, Long> {
    Boolean existsBySubcategoryName(String subcategoryName);

    Subcategory findBySubcategoryName(String name);
    @Query(value = "SELECT s FROM Subcategory s left join s.category c where c.categoryName = ?1")
    List<Subcategory> getSubcategoriesByCategoryName(String categoryName);
}
