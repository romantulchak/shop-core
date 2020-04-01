package org.computerShop.repository;

import org.computerShop.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepo extends JpaRepository<Brand, Long> {
    boolean existsBrandByName(String name);
}
