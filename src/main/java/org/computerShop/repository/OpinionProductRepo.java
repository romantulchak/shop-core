package org.computerShop.repository;

import org.computerShop.model.OpinionProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpinionProductRepo extends JpaRepository<OpinionProduct, Long> {

    //TODO: викидує нул
    @Query(value = "SELECT avg(p.rating) FROM OpinionProduct p where p.commentToProduct.id = ?1")
    double average(long id);


    @Query(value = "SELECT p FROM OpinionProduct p  where p.commentToProduct.id = ?1 order by p.id DESC ")
    List<OpinionProduct> findAllForProduct(long productId);


}
