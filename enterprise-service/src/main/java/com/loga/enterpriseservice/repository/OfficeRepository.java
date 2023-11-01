package com.loga.enterpriseservice.repository;

import com.loga.enterpriseservice.entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface OfficeRepository extends JpaRepository<Office,Long> {
    Office findByName(String name);
}
