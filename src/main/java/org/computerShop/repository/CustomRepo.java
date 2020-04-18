package org.computerShop.repository;

import org.computerShop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.computerShop.model.Custom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;
@Repository
public interface CustomRepo extends JpaRepository<Custom, Long> {

    List<Custom> findAllByOrderByIdDesc();
    Custom findFirstByIdentificationNumber(String identificationNumber);
    List<Custom> findAllByIdentificationNumber(String identificationNumber);

}
