package org.computerShop.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Replay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.OpinionsFull.class)
    private long id;

    @Size(max = 600)
    @JsonView(Views.OpinionsFull.class)
    private String message;

    @ManyToOne
    private OpinionProduct opinionProducts;

    @ManyToOne
    @JsonView(Views.OpinionsFull.class)
    private User user;

    @JsonView(Views.OpinionsFull.class)
    private LocalDateTime dateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMassage() {
        return message;
    }

    public void setMassage(String massage) {
        this.message = massage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OpinionProduct getOpinionProducts() {
        return opinionProducts;
    }

    public void setOpinionProducts(OpinionProduct opinionProducts) {
        this.opinionProducts = opinionProducts;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
