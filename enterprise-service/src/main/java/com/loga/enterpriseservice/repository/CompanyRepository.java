package com.loga.enterpriseservice.repository;

import com.loga.enterpriseservice.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    Company findByName(String name);
}
