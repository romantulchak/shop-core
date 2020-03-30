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


    @JsonView(Views.CustomFUll.class)
    private int amount;


    public CustomProduct(){

    }
    public CustomProduct(Custom custom, Product product, int amount){
        this.custom = custom;
        this.product = product;
        this.amount = amount;
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
}
