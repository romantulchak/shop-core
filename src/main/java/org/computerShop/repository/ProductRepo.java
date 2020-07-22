package org.computerShop.repository;

import org.computerShop.dto.ProductDTO;
import org.computerShop.model.Category;
import org.computerShop.model.Product;
import org.computerShop.model.Subcategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {




    List<Product> getAllByOrderByProductPrice();
    Page<Product> findAllBySubcategory(Subcategory category, Pageable pageable);
    @Query(value = "SELECT p FROM Product p left join p.category c where c.categoryName = ?1")
    Page<Product> findByCategory(String categoryName, Pageable pageable);
    @Query(value = "SELECT p FROM Product p where p.brand.name IN ?1")
    List<Product> findAllByBrand(String[] name);

    @Query(value = "SELECT p FROM Product p where p.brand.name IN ?1 AND p.cpu.name IN ?2")
    List<Product> findAllByBrandAndCpu(String[] brands, String[] cpus);


    @Query(value = "SELECT p FROM Product p where p.brand.name IN ?1 AND p.gpu.name IN ?2")
    List<Product> findAllByBrandAndGpu(String[] brands, String[] gpus);

    @Query(value = "SELECT p FROM Product p where p.cpu.name IN ?1 AND p.gpu.name IN ?2")
    List<Product> findAllByCpuAndGpu(String[] cpus, String[] gpus);

    @Query(value = "SELECT p FROM Product p where p.cpu.name IN ?1")
    List<Product> findAllByCpu(String[] name);

    @Query(value = "SELECT p FROM Product p where p.gpu.name IN ?1")
    List<Product> findAllByGpu(String[] name);

    @Query(value = "SELECT p FROM Product p where p.brand.name IN ?1 and  p.cpu.name IN ?2 and p.gpu.name IN ?3")
    List<Product> findAllByBrandAndCpuAndGpu(String[] brands, String[] cpus, String[] gpus);

    @Query(value = "SELECT p FROM Product p where p.promotionalCodes = ?1")
    Product findByPromotionalCode(long id);


    List<Product> findTop5ByOrderByNumberOfBuyDesc();

    List<Product> findFirst8ByOrderByIdDesc();



    @Query(value = "SELECT  p FROM Product p where p.id not in ?1 and p.subcategory.subcategoryName = ?2  and p.amountInStock > 0 order by p.id desc")
    List<Product> similarProducts(long productId, String subcategoryName);

    List<Product> findAllByProductNameLowercaseIsContaining(String productName);


}
