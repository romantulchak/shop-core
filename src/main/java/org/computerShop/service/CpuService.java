package org.computerShop.service;

import org.computerShop.model.accessory.CPU;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CpuService {

    List<CPU> getAllCpus();

    ResponseEntity<String> createCpu(CPU cpu);
}
