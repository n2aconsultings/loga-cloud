package com.loga.enterpriseservice.service;

import com.loga.enterpriseservice.entity.Employee;
import com.loga.enterpriseservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> list() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee read(Long id) {
        Employee employee = null;
        if(employeeRepository.findById(id).isPresent())
            employee = employeeRepository.findById(id).get();
        return employee;
    }

    @Override
    @Transactional
    public void edit(Employee employee, Long id) {
        employeeRepository
                .findById(id)
                .ifPresent(up -> {
                    up.setName(employee.getName());
                    up.setSurname(employee.getSurname());
                    up.setBirthdate(employee.getBirthdate());
                    up.setAddress(employee.getAddress());
                    up.setEmail(employee.getEmail());
                    up.setPhone(employee.getPhone());
                    employeeRepository.saveAndFlush(up);
                });
    }

    @Override
    public void delete(Long id) {
        employeeRepository
                .findById(id)
                .ifPresent(employee -> {
                    employeeRepository.delete(employee);
                });
    }
}
