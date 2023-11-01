package com.loga.logisticservice.service;

import com.loga.logisticservice.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private IProductService productService;

    @Test
    void create() {
        Product product = productService.create(new Product(
                null,
                "reference",
                "category",
                "brand",
                "designation",
                "description",
                1,
                1,
                1
        ));

        assertNotNull(product,"Failed");
    }
}