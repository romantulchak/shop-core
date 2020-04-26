package org.computerShop.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class OpinionProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.ProductFull.class)
    private long id;

    @ManyToOne
    private Product commentToProduct;

    @ManyToOne
    @JsonView(Views.ProductFull.class)
    private User user;

    @Size(max = 1000000)
    @JsonView(Views.ProductFull.class)
    private String text;

    @Size(max = 500)
    @JsonView(Views.ProductFull.class)
    private String advantages;


    @Size(max = 500)
    @JsonView(Views.ProductFull.class)
    private String disadvantages;

    @ManyToMany
    @JoinTable(
            name = "opinions_likes",
            joinColumns = @JoinColumn(name = "opinion_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonView(Views.ProductFull.class)
    private Set<User> likes = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "opinions_dislikes",
                joinColumns = @JoinColumn(name = "opinion_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> dislikes = new HashSet<>();

    @JsonView(Views.ProductFull.class)
    private LocalDateTime dateTime;

    @JsonView(Views.ProductFull.class)
    private short rating = 5;

    public OpinionProduct(Product commentToProduct, User user, String text, LocalDateTime dateTime, short rating, String advantages, String disadvantages) {
        this.commentToProduct = commentToProduct;
        this.user = user;
        this.text = text;
        this.dateTime = dateTime;
        this.rating = rating;
        this.advantages = advantages;
        this.disadvantages = disadvantages;
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
}
