package org.computerShop.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;


public class Item {

    private Long id;
    private int amount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
