package com.loga.enterpriseservice.service;

import com.loga.enterpriseservice.entity.Employee;

import java.util.List;

public interface IEmployeeService {
    Employee create(Employee employee);
    Employee read(Long id);
    List<Employee> list();
    void edit(Employee employee, Long id);
    void delete(Long id);
}
