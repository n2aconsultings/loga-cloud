package com.loga.enterpriseservice.service;

import com.loga.enterpriseservice.entity.Department;
import com.loga.enterpriseservice.repository.AssetsRepository;
import com.loga.enterpriseservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department create(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department read(Long id) {
        Department department = null;
        if(departmentRepository.findById(id).isPresent())
            department = departmentRepository.findById(id).get();
        return department;
    }

    @Override
    public Department read(String name) {
        return departmentRepository.findByName(name);
    }

    @Override
    public List<Department> list() {
        return departmentRepository.findAll();
    }

    @Override
    public void edit(Department department, Long id) {
        departmentRepository
                .findById(id)
                .ifPresent(up -> {
                    up.setName(department.getName());
                    up.setChief(department.getChief());
                    departmentRepository.saveAndFlush(department);
                });
    }

    @Override
    public void delete(Long id) {
        departmentRepository
                .findById(id)
                .ifPresent(department -> {
                    departmentRepository.delete(department);
                });
    }
}
