package org.computerShop.repository;

import org.computerShop.model.Brand;
import org.computerShop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BrandRepo extends JpaRepository<Brand, Long> {
    boolean existsBrandByName(String name);
    List<Brand> findAllByName(String name);
}
