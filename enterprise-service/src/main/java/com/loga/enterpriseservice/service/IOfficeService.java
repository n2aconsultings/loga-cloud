package com.loga.enterpriseservice.service;

import com.loga.enterpriseservice.entity.Office;

import java.util.List;

public interface IOfficeService {
    Office create(Office office);
    Office read(Long id);
    List<Office> list();
    void edit(Office office, Long id);
    void delete(Long id);
}
