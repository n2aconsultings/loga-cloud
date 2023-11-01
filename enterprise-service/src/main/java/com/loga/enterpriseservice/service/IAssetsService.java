package com.loga.enterpriseservice.service;

import com.loga.enterpriseservice.entity.Assets;

import java.util.List;

public interface IAssetsService {
    Assets create(Assets assets);
    Assets read(Long id);
    Assets read(String reference);
    List list();
    void edit(Assets assets, Long id);
    void delete(Long id);
}
