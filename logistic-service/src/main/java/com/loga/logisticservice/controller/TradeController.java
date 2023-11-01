package com.loga.logisticservice.controller;

import com.loga.logisticservice.app.api.FinanceServiceProxy;
import com.loga.logisticservice.entity.Item;
import com.loga.logisticservice.entity.Sale;
import com.loga.logisticservice.service.ITradeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/logistic-service")
public class TradeController {

    private final ITradeService tradeService;
    private final FinanceServiceProxy financeServiceProxy;

    @Autowired
    public TradeController(ITradeService tradeService,
                           FinanceServiceProxy financeServiceProxy
    ) {
        this.tradeService = tradeService;
        this.financeServiceProxy = financeServiceProxy;
    }

    @PostMapping(path = "/sales", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sale> registrateSAle(@RequestBody Sale sale){
        Sale created = tradeService.create(sale);
        return ResponseEntity.ok(created);
    }

    @GetMapping(path = "/sales", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sale>> readSales(){
        List<Sale> sales = tradeService.list();
        return ResponseEntity.ok(sales);
    }

    @GetMapping(path = "/sales/period", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sale>> readSales(@RequestParam Date start, @RequestParam Date end){
        List<Sale> sales = tradeService.list(start,end);
        return ResponseEntity.ok(sales);
    }

    @GetMapping(path = "/sales/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sale> readSale(@PathVariable Long id){
        Sale sale = tradeService.read(id);
        return ResponseEntity.ok(sale);
    }

    @DeleteMapping(path = "/sales/{id}")
    public void deleteSale(@PathVariable Long id){
        // todo : delete sale
    }

    @PutMapping(path = "/sales/{id}/item")
    public void addItem(@RequestBody Item item, @PathVariable Long id){
        tradeService.edit(item,id);
    }

    @DeleteMapping(path = "/sales/article/{id}")
    public void deleteItem(@PathVariable Long id){
        // todo : delete item
    }

    @GetMapping(path = "/sales/sale/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void invoiceSale(HttpServletResponse response, @PathVariable Long id){
        financeServiceProxy.invoiceSale(response,id);
    }
}
