package com.loga.logisticservice.service;

import com.loga.logisticservice.entity.Item;
import com.loga.logisticservice.entity.Sale;
import com.loga.logisticservice.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TradeService implements ITradeService {

    @Autowired
    private SaleRepository saleRepository;

    @Override
    public Sale create(Sale sale) {
        for (Item item:sale.getItems()) {
            if(item.getProduct().getStock()>=item.getQuantity()){
                item.getProduct().setStock(item.getProduct().getStock()-item.getQuantity());
            }
        }
        return saleRepository.save(sale);
    }

    @Override
    public List<Sale> list() {
        return saleRepository.findAll();
    }

    @Override
    public List<Sale> list(Date date) {
        return saleRepository.findAllBySaleAt(date);
    }

    @Override
    public List<Sale> list(Date start, Date end) {
        return saleRepository.findAllBySaleAtBetween(start,end);
    }

    @Override
    public Sale read(Long id) {
        if(saleRepository.findById(id).isPresent())
            return saleRepository.findById(id).get();
        return null;
    }

    @Override
    public void edit(Item item, Long id) {
        if(item.getProduct().getStock() >= item.getQuantity()){
            item.getProduct().setStock(item.getProduct().getStock() - item.getQuantity());
        }
        saleRepository
                .findById(id)
                .ifPresent(sale -> {
                    sale.getItems().add(item);
                    saleRepository.saveAndFlush(sale);
                });
    }
}
