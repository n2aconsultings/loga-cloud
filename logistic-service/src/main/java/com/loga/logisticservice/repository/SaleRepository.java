package com.loga.logisticservice.repository;

import com.loga.logisticservice.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {
    List<Sale> findAllBySaleAt(Date date);
    List<Sale> findAllBySaleAtBetween(Date start, Date end);
}
