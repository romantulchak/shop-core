package org.computerShop.repository;

import org.computerShop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.computerShop.model.Custom;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface CustomRepo extends JpaRepository<Custom, Long> {

    Custom findFirstByIdentificationNumber(String identificationNumber);
    List<Custom> findAllByIdentificationNumber(String identificationNumber);

}
