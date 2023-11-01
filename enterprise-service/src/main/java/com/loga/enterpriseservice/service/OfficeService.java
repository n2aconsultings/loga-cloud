package com.loga.enterpriseservice.service;

import com.loga.enterpriseservice.entity.Office;
import com.loga.enterpriseservice.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OfficeService implements IOfficeService {

    @Autowired
    private OfficeRepository officeRepository;

    @Override
    @Transactional
    public Office create(Office office) {
        return officeRepository.save(office);
    }

    @Override
    public Office read(Long id) {
        Office office = null;
        if(officeRepository.findById(id).isPresent())
            office = officeRepository.findById(id).get();
        return office;
    }

    @Override
    public List<Office> list() {
        return officeRepository.findAll();
    }

    @Override
    @Transactional
    public void edit(Office office, Long id) {
        officeRepository
                .findById(id)
                .ifPresent(up -> {
                    up.setName(office.getName());
                    up.setEmail(office.getEmail());
                    up.setPhone(office.getPhone());
                    up.setIfu(office.getIfu());
                    up.setAddress(office.getAddress());
                    up.setManager(office.getManager());
                    up.setAssets(office.getAssets());
                    up.setDepartments(office.getDepartments());
                    officeRepository.saveAndFlush(up);
                });
    }

    @Override
    public void delete(Long id) {
        officeRepository
                .findById(id)
                .ifPresent(office -> {
                    officeRepository.deleteById(id);
                });
    }
}
