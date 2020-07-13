package org.computerShop.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.computerShop.model.accessory.CPU;
import org.computerShop.model.accessory.GPU;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "products")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonView({Views.ProductFull.class, Views.CustomFUll.class, Views.UserFull.class,Views.SubcategoryFull.class})
    private long id;

    @Column(name = "productName")
    @JsonView({Views.ProductFull.class,Views.CustomFUll.class,Views.UserFull.class,Views.SubcategoryFull.class})
    private String productName;

    @Column(name = "productPrice")
    @JsonView({Views.ProductFull.class,Views.CustomFUll.class,Views.UserFull.class,Views.SubcategoryFull.class})
    private int productPrice;

    @Column(length = 100000)
    @JsonView({Views.ProductFull.class,Views.SubcategoryFull.class})
    private String description;

    @JsonView({Views.ProductFull.class,Views.SubcategoryFull.class})
    private int discountPrice;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    @JsonView({Views.ProductFull.class,Views.CustomFUll.class, Views.UserFull.class,Views.SubcategoryFull.class})
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Image> image;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonView({Views.ProductFull.class,Views.CustomFUll.class, Views.UserFull.class,Views.SubcategoryFull.class})
    private Subcategory subcategory;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "brand_id", nullable = false)
    @JsonView({Views.ProductFull.class,Views.CustomFUll.class, Views.UserFull.class,Views.SubcategoryFull.class})
    private Brand brand;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<CustomProduct> customProducts;

    @OneToMany(mappedBy = "commentToProduct", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonView({Views.ProductFull.class,Views.SubcategoryFull.class})
    private List<OpinionProduct> opinionProducts;

    @JsonView({Views.ProductFull.class,Views.SubcategoryFull.class})
    private int amountInStock;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "cpu_id")
    @JsonView({Views.ProductFull.class,Views.SubcategoryFull.class})
    private CPU cpu;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="gpu_id")
    @JsonView({Views.ProductFull.class,Views.SubcategoryFull.class})
    private GPU gpu;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.REMOVE)
    @JsonView({Views.ProductFull.class,Views.SubcategoryFull.class})
    private Set<PromotionalCode> promotionalCodes;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    @JsonView({Views.ProductFull.class,Views.SubcategoryFull.class})
    private List<ProductSection> productSection = new ArrayList<>();

    private transient Map<String, LinkedHashMap<String, String>> properties;

    private int numberOfBuy = 0;

    @JsonView({Views.ProductFull.class,Views.SubcategoryFull.class})
    private boolean isGlobalDiscount = false;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
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

    public Subcategory getSubcategory() {
        return subcategory;
    }


    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
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

    public List<OpinionProduct> getOpinionProducts() {
        return opinionProducts;
    }

    public void setOpinionProducts(List<OpinionProduct> opinionProducts) {
        this.opinionProducts = opinionProducts;
    }


    public List<ProductSection> getProductSection() {
        return productSection;
    }

    public void setProductSection(List<ProductSection> productSection) {
        this.productSection = productSection;
    }

    public Map<String, LinkedHashMap<String, String>> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, LinkedHashMap<String, String>> properties) {
        this.properties = properties;
    }

    @PreRemove
    public void delete(){
        this.setSubcategory(null);
        this.setBrand(null);
        this.setCpu(null);
        this.setGpu(null);
    }


}
