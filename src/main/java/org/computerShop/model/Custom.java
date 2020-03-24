package org.computerShop.model;


import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.swing.text.View;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
public class Custom  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.CustomFUll.class)
    private long id;

    @NotNull
    @JsonView(Views.CustomFUll.class)
    private String costumerName;
    @NotNull
    @JsonView(Views.CustomFUll.class)
    private String costumerLastName;

    @NotNull
    @JsonView(Views.CustomFUll.class)
    private String costumerAddress;

    @NotNull
    @JsonView(Views.CustomFUll.class)
    private String costumerCity;

    @NotNull
    @JsonView(Views.CustomFUll.class)
    private String customerMobilePhone;
    @NotNull
    @JsonView(Views.CustomFUll.class)
    private String customerPostalCode;

    @NotNull
    @JsonView(Views.CustomFUll.class)
    private int amount;

    @JsonView(Views.CustomFUll.class)
    private boolean isBeingProcessed = true;

    @JsonView(Views.CustomFUll.class)
    private boolean isCompleted = false;

    @JsonView(Views.CustomFUll.class)
    private boolean inTransit = false;

    @JsonView(Views.CustomFUll.class)
    private boolean atTheDestination = false;

    @JsonView(Views.CustomFUll.class)
    private boolean received = false;

    @NotNull
    @JsonView(Views.CustomFUll.class)
    private int totalPrice;

    @NotNull
    @Email
    @JsonView(Views.CustomFUll.class)
    private String email;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonView(Views.CustomFUll.class)
    private LocalDateTime createdDate;

    @NotNull
    @JsonView(Views.CustomFUll.class)
    private String identificationNumber;

    public Custom(){

    }

    public Custom(String costumerName, String costumerLastName, String costumerAddress, String costumerCity,
                  String customerMobilePhone, String customerPostalCode, int amount, boolean isBeingProcessed,
                  boolean isCompleted, boolean inTransit, boolean atTheDestination, boolean received
                  ){
        this.costumerName = costumerName;
        this.costumerLastName = costumerLastName;
        this.costumerAddress = costumerAddress;
        this.costumerCity = costumerCity;
        this.customerMobilePhone = customerMobilePhone;
        this.customerPostalCode = customerPostalCode;
        this.amount = amount;
        this.isBeingProcessed = isBeingProcessed;
        this.isCompleted = isCompleted;
        this.inTransit = inTransit;
        this.atTheDestination = atTheDestination;
        this.received = received;

    }

    public boolean isBeingProcessed() {
        return isBeingProcessed;
    }

    public boolean isReceived() {
        return received;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }

    public void setBeingProcessed(boolean beingProcessed) {
        isBeingProcessed = beingProcessed;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isInTransit() {
        return inTransit;
    }

    public void setInTransit(boolean inTransit) {
        this.inTransit = inTransit;
    }

    public boolean isAtTheDestination() {
        return atTheDestination;
    }

    public void setAtTheDestination(boolean atTheDestination) {
        this.atTheDestination = atTheDestination;
    }




    public void setId(long id) {
        this.id = id;
    }


    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToOne
    @JsonView(Views.CustomFUll.class)
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }



    private transient List<Item> items;


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    public long getId(){
        return id;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getCostumerName() {
        return costumerName;
    }

    public void setCostumerName(String costumerName) {
        this.costumerName = costumerName;
    }

    public String getCostumerLastName() {
        return costumerLastName;
    }

    public void setCostumerLastName(String costumerLastName) {
        this.costumerLastName = costumerLastName;
    }

    public String getCostumerAddress() {
        return costumerAddress;
    }

    public void setCostumerAddress(String costumerAddress) {
        this.costumerAddress = costumerAddress;
    }

    public String getCostumerCity() {
        return costumerCity;
    }

    public void setCostumerCity(String costumerCity) {
        this.costumerCity = costumerCity;
    }

    public String getCustomerMobilePhone() {
        return customerMobilePhone;
    }

    public void setCustomerMobilePhone(String customerMobilePhone) {
        this.customerMobilePhone = customerMobilePhone;
    }

    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
