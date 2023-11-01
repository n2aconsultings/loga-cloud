package com.loga.enterpriseservice.service;

import com.loga.enterpriseservice.entity.Assets;
import com.loga.enterpriseservice.repository.AssetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetsService implements IAssetsService
{
    @Autowired
    private AssetsRepository assetsRepository;

    @Override
    public Assets create(Assets assets) {
        return assetsRepository.save(assets);
    }

    @Override
    public Assets read(Long id) {
        Assets assets = null;
        if(assetsRepository.findById(id).isPresent())
            assets = assetsRepository.findById(id).get();
        return assets;
    }

    @Override
    public Assets read(String reference) {
        return assetsRepository.findByReference(reference);
    }

    @Override
    public List<Assets> list() {
        return assetsRepository.findAll();
    }

    @Override
    public void edit(Assets assets, Long id) {
        assetsRepository
                .findById(id)
                .ifPresent(up -> {
                    up.setName(assets.getName());
                    up.setReference(assets.getReference());
                    up.setEntryAt(assets.getEntryAt());
                    assetsRepository.saveAndFlush(up);
                });
    }

    @Override
    public void delete(Long id) {
        assetsRepository
                .findById(id)
                .ifPresent(assets -> {
                    assetsRepository.delete(assets);
                });
    }
}
