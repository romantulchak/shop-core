package org.computerShop.service;

import org.computerShop.model.Brand;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BrandService {

    List<Brand> getAll();
    ResponseEntity<String> createBrand(Brand brand);
    ResponseEntity<String> editBrand(Brand brand);
    ResponseEntity<String> deleteBrand(Brand brand);



}
