package org.computerShop.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
public class Fields {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonView(Views.CategoryFull.class)
    private String name;

    @ManyToOne
    private Sections sections;

    public Fields(String name, Sections sections){
        this.name = name;
        this.sections = sections;
    }

    public Fields() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sections getSections() {
        return sections;
    }

    public void setSections(Sections sections) {
        this.sections = sections;
    }
}
