package com.loga.logisticservice.service;


import com.loga.logisticservice.entity.Item;
import com.loga.logisticservice.entity.Sale;

import java.util.Date;
import java.util.List;

public interface ITradeService {
    Sale create(Sale sale);
    List<Sale> list();
    List<Sale> list(Date date);
    List<Sale> list(Date debut, Date fin);
    Sale read(Long id);
    void edit(Item item, Long id);
}
