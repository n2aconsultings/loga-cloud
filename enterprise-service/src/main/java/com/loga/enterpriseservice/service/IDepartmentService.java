package com.loga.enterpriseservice.service;

import com.loga.enterpriseservice.entity.Department;

import java.util.List;

public interface IDepartmentService {
    Department create(Department department);
    Department read(Long id);
    Department read(String name);
    List<Department> list();
    void edit(Department department, Long id);
    void delete(Long id);
}
