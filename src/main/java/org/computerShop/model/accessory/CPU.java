package org.computerShop.model.accessory;

import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.model.Product;
import org.computerShop.model.Views;

import javax.persistence.*;
import java.util.Set;

@Entity
public class CPU {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.ProductFull.class)
    private long id;

    @JsonView(Views.ProductFull.class)
    private String name;
    @JsonView(Views.ProductFull.class)
    private String minFrequency;
    @JsonView(Views.ProductFull.class)
    private String maxFrequency;


    @ManyToMany(mappedBy = "cpus")
    private Set<Product> products;

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

    public String getMinFrequency() {
        return minFrequency;
    }

    public void setMinFrequency(String minFrequency) {
        this.minFrequency = minFrequency;
    }

    public String getMaxFrequency() {
        return maxFrequency;
    }

    public void setMaxFrequency(String maxFrequency) {
        this.maxFrequency = maxFrequency;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
