package org.computerShop.repository;

import org.computerShop.model.Brand;
import org.computerShop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepo extends JpaRepository<Brand, Long> {
    boolean existsBrandByName(String name);
    List<Brand> findAllByName(String name);
}
