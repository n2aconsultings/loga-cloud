package com.loga.logisticservice.service;

import com.loga.logisticservice.entity.Item;
import com.loga.logisticservice.entity.Product;
import com.loga.logisticservice.entity.Sale;
import com.netflix.discovery.converters.Auto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TradeServiceTest {

    @Autowired
    private ITradeService tradeService;

    @Test
    void create() {
        List<Item> items = new ArrayList<>();
        items.add(
                new Item(
                        null,
                        new Product(
                                null,
                                "reference",
                                "category",
                                "brand",
                                "designation",
                                "description",
                                1,
                                1,
                                1
                        ),
                        1,
                        1,
                        1
                )
        );

        Sale sale = tradeService.create(new Sale(
                null,
                new Date(),
                "CLIENT",
                items
        ));

        assertNotNull(sale,"Failed");
    }
}