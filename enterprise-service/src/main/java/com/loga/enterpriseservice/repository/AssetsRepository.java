package com.loga.enterpriseservice.repository;

import com.loga.enterpriseservice.entity.Assets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetsRepository extends JpaRepository<Assets,Long> {
    Assets findByReference(String reference);
}
