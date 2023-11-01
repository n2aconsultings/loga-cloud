package com.loga.enterpriseservice.controller;

import com.loga.enterpriseservice.entity.*;
import com.loga.enterpriseservice.service.ICompanyService;
import com.loga.enterpriseservice.service.IContractService;
import com.loga.enterpriseservice.service.IOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/enterprise-service")
public class EntepriseController {

    private final ICompanyService companyService;
    private final IContractService contractService;
    private final IOfficeService officeService;

    @Autowired
    public EntepriseController(ICompanyService companyService,
                               IContractService contractService,
                               IOfficeService officeService
    ) {
        this.companyService = companyService;
        this.contractService = contractService;
        this.officeService = officeService;
    }


    @PostMapping(path = "/companies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Company> registrate(@RequestBody Company company){
        Company created = companyService.create(company);
        return ResponseEntity.ok(created);
    }

    @GetMapping(path = "/companies/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Company> read(@PathVariable Long id){
        Company company = companyService.read(id);
        return ResponseEntity.ok(company);
    }

    @PutMapping(path = "/companies/{id}")
    public void edit(@RequestBody Company company, @PathVariable Long id){
        companyService.edit(company, id);
    }

    @DeleteMapping(path = "/companies/{id}")
    public void delete(@PathVariable Long id){
        companyService.delete(id);
    }

    @PutMapping(path = "/companies/office/{id}")
    public void edit(@RequestBody Office office, @PathVariable Long id){
        Company company = companyService.read(id);
        company.getOffices().add(office);
        companyService.edit(company, company.getId());
    }

    @DeleteMapping(path = "/companies/office/{id}")
    public void deleteOffice(@PathVariable Long id){
        officeService.delete(id);
    }

    @PutMapping(path = "/companies/contract/{id}")
    public void edit(@RequestBody Contract contract, @PathVariable Long id){
        Company company = companyService.read(id);
        company.getContracts().add(contract);
        companyService.edit(company, company.getId());
    }

    @DeleteMapping(path = "/companies/contract/{id}")
    public void deleteContract(@PathVariable Long id){
        contractService.delete(id);
    }
}
