package org.computerShop.model.accessory;

import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.model.Product;
import org.computerShop.model.Views;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class CPU implements Serializable {

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

    @JsonView(Views.ProductFull.class)
    private int numberOfCores;

    @JsonView(Views.ProductFull.class)
    private String generation;

    @JsonView(Views.ProductFull.class)
    private int cacheL3;

    @JsonView(Views.ProductFull.class)
    private boolean integratedGraphics;


    @OneToMany(mappedBy = "cpu", cascade = CascadeType.ALL)
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

    public int getNumberOfCores() {
        return numberOfCores;
    }

    public void setNumberOfCores(int numberOfCores) {
        this.numberOfCores = numberOfCores;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public int getCacheL3() {
        return cacheL3;
    }

    public void setCacheL3(int cacheL3) {
        this.cacheL3 = cacheL3;
    }

    public boolean isIntegratedGraphics() {
        return integratedGraphics;
    }

    public void setIntegratedGraphics(boolean integratedGraphics) {
        this.integratedGraphics = integratedGraphics;
    }
}
