package org.computerShop.service.impl;

import org.computerShop.model.accessory.CPU;
import org.computerShop.repository.CpuRepo;
import org.computerShop.service.CpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CpuServiceImpl implements CpuService {


    private CpuRepo cpuRepo;

    @Autowired
    public CpuServiceImpl(CpuRepo cpuRepo){
        this.cpuRepo = cpuRepo;
    }


    @Override
    public List<CPU> getAllCpus() {
        return cpuRepo.findAll();
    }

    @Override
    public ResponseEntity<String> createCpu(CPU cpu) {
        if (cpu != null){
            cpuRepo.save(cpu);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Bad", HttpStatus.OK);
    }
}
