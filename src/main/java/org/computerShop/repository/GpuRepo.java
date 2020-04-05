package org.computerShop.repository;

import org.computerShop.model.accessory.GPU;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GpuRepo extends JpaRepository<GPU, Long> {
}
