package org.computerShop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.dto.CategorySectionDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "categories")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.ProductFull.class, Views.CategoryFull.class, Views.UserFull.class})
    private long id;

    @Column(name = "categoryName")
    @JsonView({Views.ProductFull.class, Views.CategoryFull.class, Views.CustomFUll.class ,Views.UserFull.class, Views.SubcategoryFull.class})
    private String categoryName;


    @Column(name = "imagePath")
    @JsonView(Views.CategoryFull.class)
    private String imagePath;


    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonView(Views.CategoryFull.class)
    private List<Subcategory> subcategories;

    @JsonView(Views.CategoryFull.class)
    private String categoryIcon;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonView({Views.CategoryFull.class,Views.ProductFull.class, Views.SubcategoryFull.class})
    private List<Sections> sectionsInDb;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Product> product;

    private transient List<CategorySectionDto> sections;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return categoryName.equals(category.categoryName);
    }

    @Override
    public int hashCode() {
        return categoryName.hashCode();
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }

    public Set<Product> getProduct() {
        return product;
    }

    public void setProduct(Set<Product> product) {
        this.product = product;
    }

    public List<CategorySectionDto> getSections() {
        return sections;
    }

    public void setSections(List<CategorySectionDto> sections) {
        this.sections = sections;
    }

    public List<Sections> getSectionsInDb() {
        return sectionsInDb;
    }

    public void setSectionsInDb(List<Sections> sectionsInDb) {
        this.sectionsInDb = sectionsInDb;
    }


}
