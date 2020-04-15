package org.computerShop.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.model.accessory.CPU;
import org.computerShop.model.accessory.GPU;

import javax.persistence.*;
import javax.swing.text.View;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonView({Views.ProductFull.class, Views.CustomFUll.class, Views.UserFull.class})
    private long id;

    @Column(name = "productName")
    @JsonView({Views.ProductFull.class,Views.CustomFUll.class,Views.UserFull.class})
    private String productName;

    @Column(name = "productPrice")
    @JsonView({Views.ProductFull.class,Views.CustomFUll.class,Views.UserFull.class})
    private int productPrice;

    @Column(length = 100000)
    @JsonView(Views.ProductFull.class)
    private String description;

    //TODO: зробити знижку для всі якщо потрібно
    @JsonView(Views.ProductFull.class)
    private int discountPrice;


    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    @JsonView({Views.ProductFull.class,Views.CustomFUll.class, Views.UserFull.class})
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Image> image;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonView({Views.ProductFull.class,Views.CustomFUll.class, Views.UserFull.class})
    private Category category;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "brand_id", nullable = false)
    @JsonView({Views.ProductFull.class,Views.CustomFUll.class, Views.UserFull.class})
    private Brand brand;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<CustomProduct> customProducts;

    @JsonView(Views.ProductFull.class)
    private int amountInStock;


    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "cpu_id")
    @JsonView(Views.ProductFull.class)
    private CPU cpu;


    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="gpu_id")
    @JsonView(Views.ProductFull.class)
    private GPU gpu;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.REMOVE)
    @JsonView(Views.ProductFull.class)
    private Set<PromotionalCode> promotionalCodes;


    private int numberOfBuy = 0;

    @JsonView(Views.ProductFull.class)
    private boolean isGlobalDiscount = false;


    @OneToMany(mappedBy = "product")
    private List<RemindMe> remindMe;


    public List<CustomProduct> getCustomProducts() {
        return customProducts;
    }

    public void setCustomProducts(List<CustomProduct> customProducts) {
        this.customProducts = customProducts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

    public void setAmountInStock(int amountInStock) {
        this.amountInStock = amountInStock;
    }


    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public GPU getGpu() {
        return gpu;
    }

    public void setGpu(GPU gpu) {
        this.gpu = gpu;
    }

    public Set<PromotionalCode> getPromotionalCodes() {
        return promotionalCodes;
    }

    public void setPromotionalCodes(Set<PromotionalCode> promotionalCodes) {
        this.promotionalCodes = promotionalCodes;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }


    public int getNumberOfBuy() {
        return numberOfBuy;
    }

    public void setNumberOfBuy(int numberOfBuy) {
        this.numberOfBuy = numberOfBuy;
    }

    public boolean isGlobalDiscount() {
        return isGlobalDiscount;
    }

    public void setGlobalDiscount(boolean globalDiscount) {
        isGlobalDiscount = globalDiscount;
    }

    public List<RemindMe> getRemindMe() {
        return remindMe;
    }

    public void setRemindMe(List<RemindMe> remindMe) {
        this.remindMe = remindMe;
    }

    @PreRemove
    public void delete(){
        this.setCategory(null);
        this.setBrand(null);
        this.setCpu(null);
        this.setGpu(null);
    }

}
