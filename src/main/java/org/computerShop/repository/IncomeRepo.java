package org.computerShop.repository;

import org.computerShop.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepo extends JpaRepository<Income, Long> {

    @Query(value = "SELECT SUM (income.income) FROM Income where income.date > current_date -interval '30' day",  nativeQuery = true)
    Long totalIncomeByMonth();
}
