package com.loga.enterpriseservice.service;

import com.loga.enterpriseservice.entity.Company;
import com.loga.enterpriseservice.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService implements ICompanyService{

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company create(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company read(Long id) {
        Company company = null;
        if(companyRepository.findById(id).isPresent()){
            company = companyRepository.findById(id).get();
        }
        return company;
    }

    @Override
    public Company read(String name) {
        Company company;
        company = companyRepository.findByName(name);
        return company;
    }

    @Override
    public List<Company> list() {
        return companyRepository.findAll();
    }

    @Override
    public void edit(Company company, Long id) {
        companyRepository
                .findById(id)
                .ifPresent(up -> {
                    up.setName(company.getName());
                    up.setPhone(company.getPhone());
                    up.setEmail(company.getEmail());
                    up.setLegal(company.getLegal());
                    up.setLocation(company.getLocation());
                    up.setContracts(company.getContracts());
                    up.setOffices(company.getOffices());
                    companyRepository.saveAndFlush(up);
                });

    }

    @Override
    public void delete(Long id) {
        companyRepository
                .findById(id)
                .ifPresent(company -> {
                    companyRepository.delete(company);
                });
    }
}
