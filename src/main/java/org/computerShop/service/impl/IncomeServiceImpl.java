package org.computerShop.service.impl;

import org.computerShop.repository.IncomeRepo;
import org.computerShop.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepo incomeRepo;
    @Autowired
    public IncomeServiceImpl(IncomeRepo incomeRepo){
        this.incomeRepo = incomeRepo;

    }

    @Override
    public long getIncomeByLastMonth(){
        if (incomeRepo.totalIncomeByMonth() != null){
            return incomeRepo.totalIncomeByMonth();
        }else{
            return 0;
        }
    }


}
