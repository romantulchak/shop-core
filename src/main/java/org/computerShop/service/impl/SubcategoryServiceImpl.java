package org.computerShop.service.impl;

import org.computerShop.dto.Field;
import org.computerShop.model.Fields;
import org.computerShop.model.Sections;
import org.computerShop.model.Subcategory;
import org.computerShop.repository.FieldsRepo;
import org.computerShop.repository.SectionsRepo;
import org.computerShop.repository.SubcategoryRepo;
import org.computerShop.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {

    private SubcategoryRepo subcategoryRepo;
    private SectionsRepo sectionsRepo;
    private FieldsRepo fieldsRepo;
    @Autowired
    public SubcategoryServiceImpl(SubcategoryRepo subcategoryRepo, SectionsRepo sectionsRepo, FieldsRepo fieldsRepo){
        this.subcategoryRepo = subcategoryRepo;
        this.sectionsRepo = sectionsRepo;
        this.fieldsRepo = fieldsRepo;
    }

    @Override
    public ResponseEntity<?> createSubcategory(Subcategory subcategory) {
       if(!subcategoryRepo.existsBySubcategoryName(subcategory.getSubcategoryName())){
           subcategoryRepo.save(subcategory);
           subcategory.getSections().forEach(e->{
               Sections sections = new Sections(e.getTitle(), subcategory);
               sectionsRepo.save(sections);
               for (Field field : e.getFields()) {
                   Fields fields1 = new Fields(field.getName(), sections);
                   fieldsRepo.save(fields1);
               }
           });
       }else{
           return new ResponseEntity<>("Subcategory with this name already exist", HttpStatus.OK);
       }

        return null;
    }

    @Override
    public List<Subcategory> getSubcategories() {
        return subcategoryRepo.findAll();
    }
}
