package com.loga.enterpriseservice.service;

import com.loga.enterpriseservice.entity.Contract;
import com.loga.enterpriseservice.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService implements IContractService{

    @Autowired
    private ContractRepository contractRepository;

    @Override
    public Contract create(Contract contract) {
        Contract created;
        created = contractRepository.save(contract);
        return created;
    }

    @Override
    public Contract read(Long id) {
        Contract contract = null;

        if(contractRepository.findById(id).isPresent())
            contract = contractRepository.findById(id).get();

        return contract;
    }

    @Override
    public Contract read(String reference) {
        Contract contract;
        contract = contractRepository.findByReference(reference);
        return contract;
    }

    @Override
    public List<Contract> list() {
        List<Contract> contracts;
        contracts = contractRepository.findAll();
        return contracts;
    }

    @Override
    public void edit(Contract contract, Long id) {
        contractRepository
                .findById(id)
                .ifPresent(up -> {
                    up.setReference(contract.getReference());
                    up.setStartAt(contract.getStartAt());
                    up.setEndAt(contract.getEndAt());
                    up.setSalary(contract.getSalary());
                    up.setPosition(contract.getPosition());
                    up.setEmployee(contract.getEmployee());
                    contractRepository.saveAndFlush(up);
                });
    }

    @Override
    public void delete(Long id) {
        contractRepository
                .findById(id)
                .ifPresent(contract -> {
                    contractRepository.delete(contract);
                });
    }
}
