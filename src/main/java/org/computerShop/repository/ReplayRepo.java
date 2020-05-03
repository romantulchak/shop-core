package org.computerShop.repository;

import org.computerShop.model.OpinionProduct;
import org.computerShop.model.Replay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplayRepo extends JpaRepository<Replay, Long> {

    @Query(value = "SELECT r FROM Replay r where r.opinionProducts.id = ?1 order by r.id DESC ")
    List<Replay> findForProduct(long opinionId);

}
