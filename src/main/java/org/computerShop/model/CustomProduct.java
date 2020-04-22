package org.computerShop.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
public class CustomProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Custom custom;

    @ManyToOne
    @JsonView({Views.CustomFUll.class, Views.UserFull.class})
    private Product product;

    @JsonView({Views.CustomFUll.class, Views.UserFull.class})
    private int amount;

    @JsonView({Views.CustomFUll.class, Views.UserFull.class})
    private int currentProductPrice;

    public CustomProduct(){

    }
    public CustomProduct(Custom custom, Product product, int amount, int currentProductPrice){
        this.custom = custom;
        this.product = product;
        this.amount = amount;
        this.currentProductPrice = currentProductPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Custom getCustom() {
        return custom;
    }

    public void setCustom(Custom custom) {
        this.custom = custom;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCurrentProductPrice() {
        return currentProductPrice;
    }

    public void setCurrentProductPrice(int currentProductPrice) {
        this.currentProductPrice = currentProductPrice;
    }
}
