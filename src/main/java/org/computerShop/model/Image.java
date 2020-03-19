package org.computerShop.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    @JsonView(Views.ProductFull.class)
    private long id;


    @JsonView(Views.ProductFull.class)
    private String imagePath;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Product product;


    public Image(){

    }
    public Image(String imagePath){
        this.product = null;
        this.imagePath = imagePath;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}

