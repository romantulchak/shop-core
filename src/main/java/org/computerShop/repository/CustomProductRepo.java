package org.computerShop.repository;

import org.computerShop.model.CustomProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomProductRepo extends JpaRepository<CustomProduct, Long> {


}
