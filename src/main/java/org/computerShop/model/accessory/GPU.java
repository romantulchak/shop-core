package org.computerShop.model.accessory;

import org.computerShop.model.Product;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
public class GPU {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private int memoryFrequency;

    @NotBlank
    private String memorySize;

    @NotBlank
    private short memoryBusWidth;

    @NotBlank
    private String memoryType;

    @OneToMany
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
