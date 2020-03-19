package org.computerShop.repository;

import org.computerShop.model.Category;
import org.computerShop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> getAllByOrderByProductPrice();
    List<Product> findAllByCategory(Category category);

}
