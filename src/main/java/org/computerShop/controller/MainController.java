package org.computerShop.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200")
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


    @PostMapping("/createProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createProduct(@RequestBody Product product){
            return productService.createProduct(product);
    }
    @DeleteMapping("/deleteProduct/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Product product){
        return this.productService.deleteProduct(product);
    }
    @PostMapping("/pushImage")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
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
    public List<Product> productsByCategory(@RequestParam(value = "categoryName", required = false) String category){
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
    public List<Product> mostPurchased(){
        return productService.mostPurchased();
    }

    @PutMapping("/setDiscountPrice/{percent}")
    @JsonView(Views.ProductFull.class)
    public ResponseEntity<String> discountPrice(@RequestBody Product product, @PathVariable("percent") short percent){
        return productService.setDiscountPrice(product, percent);
    }

    @GetMapping("/lastTenProducts")
    @JsonView(Views.ProductFull.class)
    public List<Product> lastTenProducts(){
        return productService.lastTenProducts();
    }

}
