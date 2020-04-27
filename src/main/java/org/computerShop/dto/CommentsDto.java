package org.computerShop.dto;

import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.model.OpinionProduct;
import org.computerShop.model.Product;
import org.computerShop.model.User;
import org.computerShop.model.Views;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class CommentsDto {

    @JsonView(Views.OpinionsFull.class)
    private Long id;

    @JsonView(Views.OpinionsFull.class)
    private String text;

    @JsonView(Views.OpinionsFull.class)
    private Product commentToProduct;

    @JsonView(Views.OpinionsFull.class)
    private String advantages;

    @JsonView(Views.OpinionsFull.class)
    private String disadvantages;

    @JsonView(Views.OpinionsFull.class)
    private Set<User> likes;

    @JsonView(Views.OpinionsFull.class)
    private Set<User> dislikes;

    @JsonView(Views.OpinionsFull.class)
    private boolean meLiked;

    @JsonView(Views.OpinionsFull.class)
    private boolean meDisliked;

    @JsonView(Views.OpinionsFull.class)
    private LocalDateTime dateTime;

    @JsonView(Views.OpinionsFull.class)
    private short rating;

    public CommentsDto(OpinionProduct opinionProduct) {
        this.id = opinionProduct.getId();
        this.text = opinionProduct.getText();
        this.advantages = opinionProduct.getAdvantages();
        this.disadvantages = opinionProduct.getDisadvantages();
        this.dateTime = opinionProduct.getDateTime();
        this.rating = opinionProduct.getRating();
        this.commentToProduct = opinionProduct.getCommentToProduct();
        this.likes = opinionProduct.getLikes();
        this.dislikes = opinionProduct.getDislikes();

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


    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }

    public Set<User> getDislikes() {
        return dislikes;
    }

    public void setDislikes(Set<User> dislikes) {
        this.dislikes = dislikes;
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


    public boolean isMeDisliked() {
        return meDisliked;
    }

    public void setMeDisliked(boolean meDisliked) {
        this.meDisliked = meDisliked;
    }


}
