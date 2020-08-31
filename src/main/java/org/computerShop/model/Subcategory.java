package org.computerShop.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.dto.CategorySectionDto;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.CategoryFull.class,Views.ProductFull.class, Views.SubcategoryFull.class})
    private Long id;

    @JsonView({Views.CategoryFull.class,Views.ProductFull.class, Views.SubcategoryFull.class})
    private String subcategoryName;

    @ManyToOne
    @JsonView({Views.SubcategoryFull.class, Views.ProductFull.class})
    private Category category;

    @OneToMany(mappedBy = "subcategory", cascade = CascadeType.ALL)
    @JsonView({Views.CategoryFull.class,Views.ProductFull.class, Views.SubcategoryFull.class})
    private List<Sections> sectionsInDb;

    @OneToMany(mappedBy = "subcategory", cascade = CascadeType.ALL)
    private Set<Product> product;


    private transient List<CategorySectionDto> sections;

    public List<CategorySectionDto> getSections() {
        return sections;
    }

    public void setSections(List<CategorySectionDto> sections) {
        this.sections = sections;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Sections> getSectionsInDb() {
        return sectionsInDb;
    }

    public void setSectionsInDb(List<Sections> sectionsInDb) {
        this.sectionsInDb = sectionsInDb;
    }

    public Set<Product> getProduct() {
        return product;
    }

    public void setProduct(Set<Product> product) {
        this.product = product;
    }


}
