package org.computerShop.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.format.annotation.DateTimeFormat;

import javax.crypto.SealedObject;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class PromotionalCode  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 8, max = 8, message = "Max size 8 and Min size 8")
    @JsonView(Views.ProductFull.class)
    private String code;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy HH:ss")
    private LocalDateTime createdDate;


    @DateTimeFormat(iso =  DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy HH:ss")
    private LocalDateTime expirationDate;

    private int numberOfUses;

    private short percent;

    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    public PromotionalCode() {
    }

    public PromotionalCode(LocalDateTime createdDate, LocalDateTime expirationDate, int numberOfUses, short percent, String code, Product product){
        this.createdDate = createdDate;
        this.expirationDate = expirationDate;
        this.numberOfUses = numberOfUses;
        this.percent = percent;
        this.setCode(code);
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getNumberOfUses() {
        return numberOfUses;
    }

    public void setNumberOfUses(int numberOfUses) {
        this.numberOfUses = numberOfUses;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(short percent) {
        this.percent = percent;
    }

}
