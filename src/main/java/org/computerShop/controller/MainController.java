package org.computerShop.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.dto.ProductDTO;
import org.computerShop.model.Category;
import org.computerShop.model.Product;
import org.computerShop.model.Views;
import org.computerShop.model.accessory.CPU;
import org.computerShop.model.accessory.GPU;
import org.computerShop.service.GpuService;
import org.computerShop.service.impl.CpuServiceImpl;
import org.computerShop.service.impl.GpuServiceImpl;
import org.computerShop.service.impl.ProductServiceImpl;
import org.computerShop.service.impl.PromotionalCodeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping("/api/products")
public class MainController {

    private ProductServiceImpl productService;
    private CpuServiceImpl cpuService;
    private GpuServiceImpl gpuService;
    private PromotionalCodeServiceImpl promotionalCodeService;
    @Autowired
    public MainController(ProductServiceImpl productService, CpuServiceImpl cpuService, GpuServiceImpl gpuService, PromotionalCodeServiceImpl promotionalCodeService){
        this.productService = productService;
        this.cpuService = cpuService;
        this.gpuService = gpuService;
        this.promotionalCodeService = promotionalCodeService;
    }

    @GetMapping
    @JsonView(Views.ProductFull.class)
    public List<Product> main(){
        return productService.allProducts();
    }

    @GetMapping("/filterByPrice")
    @JsonView(Views.ProductFull.class)
    public List<Product> filterByPrice(){
        return productService.filterByPrice();
    }

    @GetMapping("/findById/{id}")
    @JsonView(Views.ProductFull.class)
    public Product findById(@PathVariable("id") Product product){
        return product;
    }



    @PostMapping("/createProduct/{notifySubscribers}")
    @PreAuthorize("hasRole('ADMIN')")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public ResponseEntity<String> createProduct(@RequestBody Product product, @PathVariable(value = "notifySubscribers", required = false) boolean notifySubscribers){
        return productService.createProduct(product,notifySubscribers);
    }
    @DeleteMapping("/deleteProduct/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Product product){
        return this.productService.deleteProduct(product);
    }
    @PostMapping("/pushImage")
    @PreAuthorize("hasRole('ADMIN')")
    public void saveImage(@RequestParam("file") MultipartFile[] files) throws Exception{
        productService.pushImage(files);
    }

    @GetMapping("/details/{id}")
    @JsonView(Views.ProductFull.class)
    public Product detailsProduct(@PathVariable("id") Product product){
        return productService.detailsProduct(product);
    }

    @GetMapping("/filterByCategory")
    @JsonView(Views.ProductFull.class)
    public List<ProductDTO> productsBySubcategory(@RequestParam(value = "categoryName", required = false) String category){
        return productService.filterByCategory(category);
    }

    @GetMapping("/filter")
    @JsonView(Views.ProductFull.class)
    public List<Product> filter(@RequestParam(value = "brands", required = false) String[] brands, @RequestParam(value = "cpus", required = false) String[] cpus, @RequestParam(value = "gpus", required = false) String[] gpus ){
        return productService.filter(brands, cpus, gpus);
    }

    @GetMapping("/cpus")
    @JsonView(Views.ProductFull.class)
    public List<CPU> getAllCpus(){
        return cpuService.getAllCpus();
    }

    @PostMapping("/createCpu")
    public ResponseEntity<String> createCpu(@RequestBody CPU cpu){
        return cpuService.createCpu(cpu);
    }

    @GetMapping("/gpus")
    @JsonView(Views.ProductFull.class)
    public List<GPU> getAllGpus(){
        return gpuService.getAllGpu();
    }
    @PostMapping("/createGpu")
    public ResponseEntity<String> createGpu(@RequestBody GPU gpu){
        return gpuService.createGpu(gpu);
    }


    //TODO: пофіксити повернення коду
    @PostMapping("/createPromo/{productId}/{percent}/{numberOfDays}/{numberOfUses}")
    public ResponseEntity<String> createPromo(@PathVariable("productId") Long id, @PathVariable("percent") short percent, @PathVariable("numberOfDays") long numberOfDays, @PathVariable("numberOfUses") int numberOfUses) {
        return promotionalCodeService.createPromo(percent, numberOfDays, numberOfUses, id);
    }

    @GetMapping("/checkDiscount/{id}")
    public ResponseEntity<?> checkDiscount(@RequestParam("code") String code, @PathVariable("id") long productId){
        return promotionalCodeService.findCode(code, productId);
    }

    @GetMapping("/mostPurchased")
    @JsonView(Views.ProductFull.class)
    public List<ProductDTO> mostPurchased(){
        return productService.mostPurchased();
    }

    @PutMapping("/setDiscountPrice/{percent}/{notifySubscribers}")
    @JsonView(Views.ProductFull.class)
    public ResponseEntity<String> discountPrice(@RequestBody Product product, @PathVariable(value = "percent", required = false) short percent, @PathVariable("notifySubscribers") boolean notifySubscribers){
        return productService.setDiscountPrice(product, percent, notifySubscribers);
    }

    @GetMapping("/lastTenProducts")
    @JsonView(Views.ProductFull.class)
    public List<ProductDTO> lastTenProducts(){
        return productService.lastTenProducts();
    }



    @PutMapping("/updateProduct")
    public ResponseEntity<?> updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @GetMapping("/similarProducts/{id}/{categoryName}")
    @JsonView(Views.ProductFull.class)
    public List<Product> similarProducts(@PathVariable("id") int productId, @PathVariable("categoryName") String categoryName){
        return productService.similarProducts(productId,categoryName);
    }
    @GetMapping("/searchProducts")
    @JsonView(Views.ProductFull.class)
    public List<ProductDTO> searchProducts(@RequestParam(value = "productName") String productName){
        return productService.searchProducts(productName.toLowerCase());
    }
}
