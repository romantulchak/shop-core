package org.computerShop.repository;

import org.computerShop.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepo extends JpaRepository<Subcategory, Long> {
    Boolean existsBySubcategoryName(String subcategoryName);

    Subcategory findBySubcategoryName(String name);
}
