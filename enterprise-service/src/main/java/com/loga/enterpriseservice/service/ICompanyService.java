package com.loga.enterpriseservice.service;

import com.loga.enterpriseservice.entity.Company;

import java.util.List;

public interface ICompanyService {
    Company create(Company company);
    Company read(Long id);
    Company read(String name);
    List<Company> list();
    void edit(Company company, Long id);
    void delete(Long id);
}
