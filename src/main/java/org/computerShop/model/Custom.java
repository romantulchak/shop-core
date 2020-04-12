package org.computerShop.model;


import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.maps.Status;
import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Custom  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.CustomFUll.class, Views.UserFull.class,Views.CustomUser.class})
    private long id;

    @NotNull
    @JsonView({Views.CustomFUll.class, Views.UserFull.class,Views.CustomUser.class})
    private String costumerName;
    @NotNull
    @JsonView({Views.CustomFUll.class, Views.UserFull.class,Views.CustomUser.class})
    private String costumerLastName;

    @NotNull
    @JsonView({Views.CustomFUll.class, Views.UserFull.class,Views.CustomUser.class})
    private String costumerAddress;

    @NotNull
    @JsonView({Views.CustomFUll.class, Views.UserFull.class,Views.CustomUser.class})
    private String costumerCity;

    @NotNull
    @JsonView({Views.CustomFUll.class, Views.UserFull.class,Views.CustomUser.class})
    private String customerMobilePhone;
    @NotNull
    @JsonView({Views.CustomFUll.class, Views.UserFull.class,Views.CustomUser.class})
    private String customerPostalCode;


    //TODO: видалити
    @NotNull
    @JsonView({Views.CustomFUll.class, Views.UserFull.class,Views.CustomUser.class})
    private int amount;

    @ElementCollection
    @JsonView({Views.CustomFUll.class, Views.UserFull.class,Views.CustomUser.class})
    private List<Status> statuses = new ArrayList<>(5);

    @NotNull
    @JsonView({Views.CustomFUll.class, Views.UserFull.class,Views.CustomUser.class})
    private int totalPrice;

    @NotNull
    @Email
    @JsonView({Views.CustomFUll.class, Views.CustomUser.class, Views.UserFull.class})
    private String email;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonView({Views.CustomFUll.class, Views.UserFull.class,Views.CustomUser.class})
    private LocalDateTime createdDate;

    @NotNull
    @JsonView({Views.CustomFUll.class, Views.UserFull.class, Views.CustomUser.class})
    private String identificationNumber;

/*
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JsonView({Views.CustomFUll.class, Views.UserFull.class,Views.CustomUser.class})
    private Product product;
*/
    @JsonView({Views.CustomFUll.class, Views.UserFull.class, Views.CustomUser.class})
    private boolean cancel = false;

    @OneToMany(mappedBy = "custom", cascade = CascadeType.REMOVE)
    @JsonView({Views.CustomFUll.class, Views.UserFull.class})
    private Set<CustomProduct> customProducts;


    @ManyToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isCancel(){
        return cancel;
    }
    public void setCancel(boolean cancel){
        this.cancel = cancel;
    }

    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }

    public Custom(){

    }

    public Custom(ArrayList<Status> statuses){
        this.setCreatedDate(LocalDateTime.now());
        this.setStatuses(statuses);

    }


    public Set<CustomProduct> getCustomProducts() {
        return customProducts;
    }

    public void setCustomProducts(Set<CustomProduct> customProducts) {
        this.customProducts = customProducts;
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


 /*   public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
*/


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
