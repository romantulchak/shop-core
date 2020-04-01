package org.computerShop.service.impl;

import org.computerShop.model.Brand;
import org.computerShop.repository.BrandRepo;
import org.computerShop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {


    private BrandRepo brandRepo;

    @Autowired
    public BrandServiceImpl(BrandRepo brandRepo){
        this.brandRepo = brandRepo;
    }




    @Override
    public List<Brand> getAll() {
        return brandRepo.findAll();
    }

    @Override
    public ResponseEntity<String> createBrand(Brand brand) {


        if(brand != null && !brandRepo.existsBrandByName(brand.getName())){
            brandRepo.save(brand);
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }
        return new ResponseEntity<>("Bad", HttpStatus.OK);


    }

    @Override
    public ResponseEntity<String> editBrand(Brand brand) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteBrand(Brand brand) {
        return null;
    }
}
