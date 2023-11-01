package com.loga.logisticservice.repository;

import com.loga.logisticservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByReference(String reference);

    List<Product> findAllByDesignationContaining(String designation);
}
