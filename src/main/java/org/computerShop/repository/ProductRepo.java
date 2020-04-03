package org.computerShop.repository;

import org.computerShop.model.Category;
import org.computerShop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> getAllByOrderByProductPrice();
    List<Product> findAllByCategory(Category category);

    @Query(value = "SELECT p FROM Product p where p.brand.name = ?1")
    List<Product> findAllByBrand(String name);
}
