package org.computerShop.service.impl;

import org.computerShop.dto.Field;
import org.computerShop.model.Fields;
import org.computerShop.model.Sections;
import org.computerShop.model.Category;
import org.computerShop.repository.CategoryRepo;
import org.computerShop.repository.FieldsRepo;
import org.computerShop.repository.SectionsRepo;
import org.computerShop.service.CategoryService;
import org.computerShop.sockets.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepo categoryRepo;
    private SimpMessagingTemplate simpMessagingTemplate;
    private SectionsRepo sectionsRepo;
    private FieldsRepo fieldsRepo;
    @Value("${upload.CategoryPath}")
    private String uploadPath;
    private String path;
    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo, SimpMessagingTemplate simpMessagingTemplate, SectionsRepo sectionsRepo, FieldsRepo fieldsRepo){
        this.categoryRepo = categoryRepo;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.sectionsRepo = sectionsRepo;
        this.fieldsRepo = fieldsRepo;
    }
    @Override
    public List<Category> getCategories() {
        return categoryRepo.findAll();
    }
    @Override
    public ResponseEntity<String> createCategory(Category category){
        if (!categoryRepo.existsByCategoryName(category.getCategoryName()) && !category.getCategoryName().isEmpty()){
            category.setImagePath(path);
            categoryRepo.save(category);
            category.getSections().forEach(e->{
                Sections sections = new Sections(e.getTitle(), category);
                sectionsRepo.save(sections);
                for (Field field : e.getFields()) {
                    Fields fields1 = new Fields(field.getName(), sections);
                    fieldsRepo.save(fields1);
                }
            });
            simpMessagingTemplate.convertAndSend("/topic/update", new ResponseMessage("updateCategory", true));
            return new ResponseEntity<>("Was created " + category.getCategoryName(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Bad", HttpStatus.OK);
        }
    }
    @Override
    public ResponseEntity<String> deleteCategory(Category category){
        if (category !=null){
            categoryRepo.delete(category);
            simpMessagingTemplate.convertAndSend("/topic/update", new ResponseMessage("updateCategory", true));
            return new ResponseEntity<>("Ok category", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Bad delete category", HttpStatus.OK);
        }
    }
    @Override
    public ResponseEntity<String> editCategory(Category category){
        if (category!=null) {
            categoryRepo.save(category);
            simpMessagingTemplate.convertAndSend("/topic/update", new ResponseMessage("updateCategory", true));
        }
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
    @Override
    public void pushImage(MultipartFile multipartFile) throws IOException {
        if(multipartFile != null){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFullName = uuidFile + "." + multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File(uploadPath + "/" + resultFullName));
            path = "http://localhost:8080/categoryImages/" + resultFullName;
        }
    }
}
