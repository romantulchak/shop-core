package org.computerShop.dto;

import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.model.Views;

import java.util.List;

public class ProductPageableDTO {

    @JsonView(Views.ProductFull.class)
    public List<ProductDTO> productDTOS;

    @JsonView(Views.ProductFull.class)
    public int currentPage;

    @JsonView(Views.ProductFull.class)
    public int totalPages;
    @JsonView(Views.ProductFull.class)
    private int productsCounter;
    @JsonView(Views.ProductFull.class)
    private long totalItems;




    public ProductPageableDTO() {
    }

    public ProductPageableDTO(List<ProductDTO> productDTOS, int currentPage, int totalPages, int productsCounter, long totalItems) {
        this.productDTOS = productDTOS;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.productsCounter = productsCounter;
        this.totalItems = totalItems;
    }

    public List<ProductDTO> getProductDTOS() {
        return productDTOS;
    }

    public void setProductDTOS(List<ProductDTO> productDTOS) {
        this.productDTOS = productDTOS;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getProductsCounter() {
        return productsCounter;
    }

    public void setProductsCounter(int productsCounter) {
        this.productsCounter = productsCounter;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }
}
