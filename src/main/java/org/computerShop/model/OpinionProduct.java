package org.computerShop.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class OpinionProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Product commentToProduct;

    @ManyToOne
    @JsonView(Views.ProductFull.class)
    private User user;

    @Size(max = 1000000)
    @JsonView(Views.ProductFull.class)
    private String text;

    @JsonView(Views.ProductFull.class)
    private LocalDateTime dateTime;

    @JsonView(Views.ProductFull.class)
    private short rating = 5;

    public OpinionProduct(Product commentToProduct, User user, String text, LocalDateTime dateTime, short rating) {
        this.commentToProduct = commentToProduct;
        this.user = user;
        this.text = text;
        this.dateTime = dateTime;
        this.rating = rating;
    }

    public OpinionProduct() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getCommentToProduct() {
        return commentToProduct;
    }

    public void setCommentToProduct(Product commentToProduct) {
        this.commentToProduct = commentToProduct;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public short getRating() {
        return rating;
    }

    public void setRating(short rating) {
        this.rating = rating;
    }
}
