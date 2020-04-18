package org.computerShop.repository;

import org.computerShop.model.accessory.CPU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CpuRepo extends JpaRepository<CPU, Long> {

}
