package org.computerShop.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.model.Product;
import org.computerShop.model.Views;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class ProductSection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonView(Views.ProductFull.class)
    private String title;

    @ElementCollection
    @JsonView(Views.ProductFull.class)
    private Map<String, String> sections = new HashMap<>();

    @ManyToOne
    private Product product;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<String, String> getSections() {
        return sections;
    }

    public void setSections(Map<String, String> sections) {
        this.sections = sections;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
