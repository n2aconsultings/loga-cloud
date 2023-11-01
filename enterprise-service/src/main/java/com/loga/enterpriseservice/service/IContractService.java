package com.loga.enterpriseservice.service;

import com.loga.enterpriseservice.entity.Contract;
import com.loga.enterpriseservice.entity.Employee;

import java.util.List;

public interface IContractService {
    Contract create(Contract contract);
    Contract read(Long id);
    Contract read(String reference);
    List<Contract> list();
    void edit(Contract contract, Long id);
    void delete(Long id);
}
