package org.computerShop.repository;

import org.computerShop.model.PromotionalCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionalCodeRepo extends JpaRepository<PromotionalCode, Long> {

    PromotionalCode findByCode(String code);


}
