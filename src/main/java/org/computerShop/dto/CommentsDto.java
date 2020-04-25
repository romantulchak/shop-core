package org.computerShop.dto;

import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.model.OpinionProduct;
import org.computerShop.model.Product;
import org.computerShop.model.Views;

import java.time.LocalDateTime;
import java.util.List;

public class CommentsDto {
    @JsonView(Views.ProductFull.class)
    private Long id;

    @JsonView(Views.ProductFull.class)
    private String text;

    private Product commentToProduct;

    @JsonView(Views.ProductFull.class)
    private String advantages;

    @JsonView(Views.ProductFull.class)
    private String disadvantages;

    @JsonView(Views.ProductFull.class)
    private Long likes;

    @JsonView(Views.ProductFull.class)
    private boolean meLiked;

    @JsonView(Views.ProductFull.class)
    private LocalDateTime dateTime;

    @JsonView(Views.ProductFull.class)
    private short rating;

    public CommentsDto(OpinionProduct opinionProduct, Long likes, boolean meLiked) {
        this.id = opinionProduct.getId();
        this.text = opinionProduct.getText();
        this.advantages = opinionProduct.getAdvantages();
        this.disadvantages = opinionProduct.getDisadvantages();
        this.likes = likes;
        this.meLiked = meLiked;
        this.dateTime = opinionProduct.getDateTime();
        this.rating = opinionProduct.getRating();
        this.commentToProduct = opinionProduct.getCommentToProduct();
    }
    public CommentsDto(OpinionProduct opinionProduct, Long likes) {
        this.id = opinionProduct.getId();
        this.text = opinionProduct.getText();
        this.advantages = opinionProduct.getAdvantages();
        this.disadvantages = opinionProduct.getDisadvantages();
        this.likes = likes;
        this.dateTime = opinionProduct.getDateTime();
        this.rating = opinionProduct.getRating();
        this.commentToProduct = opinionProduct.getCommentToProduct();
    }
    public CommentsDto(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAdvantages() {
        return advantages;
    }

    public void setAdvantages(String advantages) {
        this.advantages = advantages;
    }

    public String getDisadvantages() {
        return disadvantages;
    }

    public void setDisadvantages(String disadvantages) {
        this.disadvantages = disadvantages;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
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

    public Product getCommentToProduct() {
        return commentToProduct;
    }

    public void setCommentToProduct(Product commentToProduct) {
        this.commentToProduct = commentToProduct;
    }

    public boolean isMeLiked() {
        return meLiked;
    }

    public void setMeLiked(boolean meLiked) {
        this.meLiked = meLiked;
    }
}
