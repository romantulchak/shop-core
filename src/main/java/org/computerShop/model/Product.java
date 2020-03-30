package org.computerShop.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.swing.text.View;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonView({Views.ProductFull.class, Views.CustomFUll.class, Views.UserFull.class})
    private long id;

    @Column(name = "productName")
    @JsonView({Views.ProductFull.class,Views.CustomFUll.class,Views.UserFull.class})
    private String productName;

    @Column(name = "productPrice")
    @JsonView({Views.ProductFull.class,Views.CustomFUll.class,Views.UserFull.class})
    private double productPrice;

    @Column
    @JsonView(Views.ProductFull.class)
    private String description;


    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    @JsonView({Views.ProductFull.class,Views.CustomFUll.class, Views.UserFull.class})
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Image> image;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonView({Views.ProductFull.class,Views.CustomFUll.class, Views.UserFull.class})
    private Category category;


/*
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Custom> customs;
*/

    @OneToMany(mappedBy = "product")
    private List<CustomProduct> customProducts;


    public List<CustomProduct> getCustomProducts() {
        return customProducts;
    }

    public void setCustomProducts(List<CustomProduct> customProducts) {
        this.customProducts = customProducts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @PreRemove
    public void deleteCategory(){
        this.setCategory(null);
    }
}
