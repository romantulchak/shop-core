package org.computerShop.repository;

import org.computerShop.model.CustomProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomProductRepo extends JpaRepository<CustomProduct, Long> {


}
