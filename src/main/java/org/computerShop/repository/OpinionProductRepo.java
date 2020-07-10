package org.computerShop.repository;

import org.computerShop.dto.CommentsDto;
import org.computerShop.model.OpinionProduct;
import org.computerShop.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpinionProductRepo extends JpaRepository<OpinionProduct, Long> {

    //TODO: викидує нул
    @Query(value = "SELECT avg(p.rating) FROM OpinionProduct p where p.commentToProduct.id = ?1")
    Double average(long id);


    @Query(value = "SELECT p FROM OpinionProduct p  where p.commentToProduct.id = ?1 order by p.id DESC ")
    Page<OpinionProduct> findAllForProduct(long productId, Pageable pageable);



    ///@Query(value = "SELECT new org.computerShop.dto.CommentsDto(m,count(ml), (sum(case when ml = :user then 1 else 0 end) > 0), count(ds), (sum (case  when ds =:user then 1 else 0 end)>0)) " +
    //        "FROM OpinionProduct m  left join m.likes ml  left join m.dislikes ds where m.commentToProduct.id = :productId group by m order by m.id DESC ")
    //Page<CommentsDto> findAllForProduct(@Param("productId") long productId, Pageable pageable,@Param("user") User user);




    @Query(value = "SELECT p FROM OpinionProduct p  where p.commentToProduct.id = ?1 order by p.id DESC ")
    List<OpinionProduct> findAllForProduct(long productId);

}
