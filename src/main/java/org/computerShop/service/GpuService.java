package org.computerShop.service;

import org.computerShop.model.accessory.CPU;
import org.computerShop.model.accessory.GPU;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GpuService {

    List<GPU> getAllGpu();
    ResponseEntity<String> createGpu(GPU cpu);
}
