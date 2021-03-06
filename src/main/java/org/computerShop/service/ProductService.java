package org.computerShop.service;

import org.computerShop.dto.ProductDTO;
import org.computerShop.dto.ProductPageableDTO;
import org.computerShop.model.Category;
import org.computerShop.model.Product;
import org.computerShop.model.accessory.CPU;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ProductService {

    List<Product> allProducts();
    List<Product> filterByPrice();
    HttpStatus pushImage(MultipartFile[] files) throws IOException;
    ResponseEntity<String> createProduct(Product product, boolean notifySubscribers);
    ResponseEntity<?> updateProduct(Product product);
    ResponseEntity<String> deleteProduct(Product product);
    Product detailsProduct(Product product);
    ProductPageableDTO filterByCategory(String category, int page);
    List<Product> filter(String[] brands, String[] cpus, String[] gpus );
    List<ProductDTO> mostPurchased();
    ResponseEntity<String> setDiscountPrice(Product product, short percent, boolean notifySubscribers);
    List<ProductDTO> lastTenProducts();
    List<Product> similarProducts(long productId, String categoryName);
    List<ProductDTO> searchProducts(String productName);
    Integer getMaxPrice(String categoryName);
    Integer getMinPrice(String categoryName);
}
