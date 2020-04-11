package org.computerShop.model.accessory;

import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.model.Product;
import org.computerShop.model.Views;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Entity
public class GPU implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.ProductFull.class)
    private long id;

    @NotBlank
    @JsonView(Views.ProductFull.class)
    private String name;
    @JsonView(Views.ProductFull.class)
    private int memoryFrequency;

    @NotBlank
    @JsonView(Views.ProductFull.class)
    private String memorySize;

    @JsonView(Views.ProductFull.class)
    private short memoryBusWidth;

    @NotBlank
    @JsonView(Views.ProductFull.class)
    private String memoryType;

    @OneToMany(mappedBy = "gpu")
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

    public int getMemoryFrequency() {
        return memoryFrequency;
    }

    public void setMemoryFrequency(int memoryFrequency) {
        this.memoryFrequency = memoryFrequency;
    }

    public String getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(String memorySize) {
        this.memorySize = memorySize;
    }

    public short getMemoryBusWidth() {
        return memoryBusWidth;
    }

    public void setMemoryBusWidth(short memoryBusWidth) {
        this.memoryBusWidth = memoryBusWidth;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
