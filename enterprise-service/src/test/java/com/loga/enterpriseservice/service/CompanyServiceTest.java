package com.loga.enterpriseservice.service;

import com.loga.enterpriseservice.entity.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CompanyServiceTest {

    @Autowired
    private ICompanyService companyService;

    @Test
    void create() {
        Company company = new Company();
        company.setName("N2A.CC");
        company.setEmail("contact@ntwoa-cc.net");
        company.setPhone("(229)97947325");
        company.setLocation("Bénin");
        company.setLegal("IFU:23333993939339");

        Company created = companyService.create(company);

        assertNotNull(created,"Failed creating Company !!!");
    }
}