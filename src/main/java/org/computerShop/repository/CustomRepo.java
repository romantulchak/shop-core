package org.computerShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.computerShop.model.Custom;

import java.util.List;

public interface CustomRepo extends JpaRepository<Custom, Long> {

    List<Custom> findAllByIdentificationNumber(String identificationNumber);

}
