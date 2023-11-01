package com.loga.financeservice.service;

import com.loga.financeservice.entity.Salary;

import java.util.List;

public interface ISalaryService {
    Salary create(Salary salary);
    List<Salary> list();
}
