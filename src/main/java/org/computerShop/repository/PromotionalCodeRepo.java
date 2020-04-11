package org.computerShop.repository;

import org.computerShop.model.PromotionalCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionalCodeRepo extends JpaRepository<PromotionalCode, Long> {

    PromotionalCode findByCode(String code);


}
