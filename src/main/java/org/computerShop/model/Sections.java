package org.computerShop.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.List;

@Entity
public class Sections {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonView(Views.CategoryFull.class)
    private String title;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "sections", cascade = CascadeType.REMOVE)
    @JsonView(Views.CategoryFull.class)
    private List<Fields>fields;


    public Sections(String title, Category category){
        this.title = title;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Fields> getFields() {
        return fields;
    }

    public void setFields(List<Fields> fields) {
        this.fields = fields;
    }
}
