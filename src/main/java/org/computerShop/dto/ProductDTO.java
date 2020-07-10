package org.computerShop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.model.*;
import org.computerShop.model.accessory.CPU;
import org.computerShop.model.accessory.GPU;

import javax.persistence.*;
import java.util.*;

public class ProductDTO {

    @JsonView({Views.ProductFull.class, Views.CustomFUll.class, Views.UserFull.class})
    private long id;

    @JsonView({Views.ProductFull.class,Views.CustomFUll.class,Views.UserFull.class})
    private String productName;


    @JsonView({Views.ProductFull.class,Views.CustomFUll.class,Views.UserFull.class})
    private int productPrice;


    @JsonView(Views.ProductFull.class)
    private String description;

    @JsonView(Views.ProductFull.class)
    private int discountPrice;


    @JsonView({Views.ProductFull.class,Views.CustomFUll.class, Views.UserFull.class})
    private List<Image> image;


    @JsonView({Views.ProductFull.class,Views.CustomFUll.class, Views.UserFull.class})
    private Category category;

    @JsonView({Views.ProductFull.class,Views.CustomFUll.class, Views.UserFull.class})
    private Brand brand;


    private List<CustomProduct> customProducts;

    @JsonView({Views.ProductFull.class})
    private List<OpinionProduct> opinionProducts;

    @JsonView(Views.ProductFull.class)
    private int amountInStock;

    @JsonView(Views.ProductFull.class)
    private CPU cpu;

    @JsonView(Views.ProductFull.class)
    private GPU gpu;

    @JsonView(Views.ProductFull.class)
    private Set<PromotionalCode> promotionalCodes;


    @JsonView(Views.ProductFull.class)
    private List<ProductSection> productSection = new ArrayList<>();

    private transient Map<String, LinkedHashMap<String, String>> properties;

    private int numberOfBuy = 0;

    @JsonView(Views.ProductFull.class)
    private boolean isGlobalDiscount = false;
    @JsonView(Views.ProductFull.class)
    private List<RemindMe> remindMe;

    @JsonView(Views.ProductFull.class)
    private Double averageRanking;
    public ProductDTO(){

    }

    public ProductDTO(Product product, Double averageRanking) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
        this.description = product.getDescription();
        this.discountPrice = product.getDiscountPrice();
        this.image = product.getImage();
        this.category = product.getCategory();
        this.brand = product.getBrand();
        this.customProducts = product.getCustomProducts();
        this.opinionProducts = product.getOpinionProducts();
        this.amountInStock = product.getAmountInStock();
        this.cpu = product.getCpu();
        this.gpu = product.getGpu();
        this.promotionalCodes = product.getPromotionalCodes();
        this.productSection = product.getProductSection();
        this.properties = product.getProperties();
        this.numberOfBuy = product.getNumberOfBuy();
        this.isGlobalDiscount = product.isGlobalDiscount();
        this.remindMe = product.getRemindMe();
        this.averageRanking = averageRanking;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
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

    public List<CustomProduct> getCustomProducts() {
        return customProducts;
    }

    public void setCustomProducts(List<CustomProduct> customProducts) {
        this.customProducts = customProducts;
    }

    public List<OpinionProduct> getOpinionProducts() {
        return opinionProducts;
    }

    public void setOpinionProducts(List<OpinionProduct> opinionProducts) {
        this.opinionProducts = opinionProducts;
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

    public Double getAverageRanking() {
        return averageRanking;
    }

    public void setAverageRanking(Double averageRanking) {
        this.averageRanking = averageRanking;
    }
}
