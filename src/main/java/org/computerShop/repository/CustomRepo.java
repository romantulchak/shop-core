package org.computerShop.repository;

import org.computerShop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.computerShop.model.Custom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface CustomRepo extends JpaRepository<Custom, Long> {

    List<Custom> findAllByOrderByIdDesc();
    Custom findFirstByIdentificationNumber(String identificationNumber);
    List<Custom> findAllByIdentificationNumber(String identificationNumber);

    @Query(value = "SELECT count(c) FROM Custom c")
    Long getTotalOrderCounter();
    @Query(value = "SELECT count(c) FROM Custom c where c.createdDate > CURRENT_DATE ")
    Long getOrderCounterByDay();
    @Query(value="SELECT sum(c.totalPrice) FROM Custom c where c.createdDate > CURRENT_DATE ")
    Long getTotalCustomPriceByDay();

    @Query(value = "SELECT SUM (custom.total_price) FROM Custom where custom.created_date > current_date - interval '30' day", nativeQuery = true)
    Long getTotalCustomPriceByMonth();
}
