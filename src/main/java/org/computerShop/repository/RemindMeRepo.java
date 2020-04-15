package org.computerShop.repository;

import org.computerShop.model.Product;
import org.computerShop.model.RemindMe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RemindMeRepo extends JpaRepository<RemindMe, Long> {
   @Query(value = "SELECT p FROM RemindMe p WHERE p.product.id = ?1")
    List<RemindMe> allForProduct(long id);
}
