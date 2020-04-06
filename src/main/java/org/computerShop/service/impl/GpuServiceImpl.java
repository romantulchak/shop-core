package org.computerShop.service.impl;

import org.computerShop.model.accessory.CPU;
import org.computerShop.model.accessory.GPU;
import org.computerShop.repository.GpuRepo;
import org.computerShop.service.GpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GpuServiceImpl implements GpuService {

    private GpuRepo gpuRepo;
    @Autowired
    public GpuServiceImpl(GpuRepo gpuRepo) {
        this.gpuRepo = gpuRepo;
    }

    @Override
    public List<GPU> getAllGpu() {
        System.out.println("sdfdsfdsf");
        return gpuRepo.findAll();
    }

    @Override
    public ResponseEntity<String> createGpu(GPU gpu) {
        if(gpu != null){
            gpuRepo.save(gpu);
            return new ResponseEntity<>("Ok", HttpStatus.OK);

        }
        return new ResponseEntity<>("Bad", HttpStatus.OK);
    }
}
