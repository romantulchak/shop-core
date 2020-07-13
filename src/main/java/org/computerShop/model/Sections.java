package org.computerShop.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.List;

@Entity
public class Sections {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.CategoryFull.class,Views.ProductFull.class,Views.SubcategoryFull.class})
    private long id;

    @JsonView({Views.CategoryFull.class,Views.ProductFull.class,Views.SubcategoryFull.class})
    private String title;

    @ManyToOne
    private Subcategory subcategory;

    @OneToMany(mappedBy = "sections", cascade = CascadeType.REMOVE)
    @JsonView({Views.CategoryFull.class, Views.ProductFull.class,Views.SubcategoryFull.class})
    private List<Fields>fields;


    public Sections(String title, Subcategory subcategory){
        this.title = title;
        this.subcategory = subcategory;
    }

    public Sections() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public List<Fields> getFields() {
        return fields;
    }

    public void setFields(List<Fields> fields) {
        this.fields = fields;
    }
}
