package org.computerShop.model;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
public class RemindMe {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Email
    private String email;


    @ManyToOne
    private Product product;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
