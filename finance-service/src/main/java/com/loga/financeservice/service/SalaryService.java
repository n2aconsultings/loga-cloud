package com.loga.financeservice.service;

import com.loga.financeservice.entity.Salary;
import com.loga.financeservice.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SalaryService implements ISalaryService{

    @Autowired
    private SalaryRepository salaryRepository;

    @Override
    @Transactional
    public Salary create(Salary salary) {
        return salaryRepository.save(salary);
    }

    @Override
    public List<Salary> list() {
        return salaryRepository.findAll();
    }
}
