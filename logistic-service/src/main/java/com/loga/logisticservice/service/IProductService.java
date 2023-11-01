package com.loga.logisticservice.service;

import com.loga.logisticservice.entity.Product;
import java.util.List;

public interface IProductService {
    Product create(Product product);
    List<Product> list();
    List<Product> list(String reference);
    Product read(Long id);
    Product read(String reference);
    void edit(Product product, Long id);
}
