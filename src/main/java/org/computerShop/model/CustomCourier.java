package org.computerShop.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class CustomCourier {


    @JsonView({Views.CustomFUll.class, Views.UserFull.class,Views.CustomUser.class})
    private String costumerCity;
    @JsonView({Views.CustomFUll.class, Views.UserFull.class,Views.CustomUser.class})
    private String costumerStreet;
    @JsonView({Views.CustomFUll.class, Views.UserFull.class,Views.CustomUser.class})
    private String costumerHouse;
    @JsonView({Views.CustomFUll.class, Views.UserFull.class,Views.CustomUser.class})
    private String costumerFlat;

    @JsonView({Views.CustomFUll.class, Views.UserFull.class,Views.CustomUser.class})
    private String customerPostalCode;

    public String getCostumerCity() {
        return costumerCity;
    }

    public void setCostumerCity(String costumerCity) {
        this.costumerCity = costumerCity;
    }

    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    public String getCostumerStreet() {
        return costumerStreet;
    }

    public void setCostumerStreet(String costumerStreet) {
        this.costumerStreet = costumerStreet;
    }

    public String getCostumerHouse() {
        return costumerHouse;
    }

    public void setCostumerHouse(String costumerHouse) {
        this.costumerHouse = costumerHouse;
    }

    public String getCostumerFlat() {
        return costumerFlat;
    }

    public void setCostumerFlat(String costumerFlat) {
        this.costumerFlat = costumerFlat;
    }
}
