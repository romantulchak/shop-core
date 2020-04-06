package org.computerShop.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.ProductFull.class,  Views.BrandFull.class})
    private long id;

    @NotBlank
    @JsonView({Views.ProductFull.class, Views.BrandFull.class})
    private String name;


    @OneToMany(mappedBy = "brand",cascade = CascadeType.ALL)
    @JsonView(Views.BrandFull.class)
    private Set<Product> products = new LinkedHashSet<>();

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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
