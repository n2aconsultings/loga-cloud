package com.loga.logisticservice.controller;

import com.loga.logisticservice.app.api.ReportServiceProxy;
import com.loga.logisticservice.entity.Delivery;
import com.loga.logisticservice.entity.Furnisher;
import com.loga.logisticservice.entity.Article;
import com.loga.logisticservice.entity.Order;
import com.loga.logisticservice.exception.ArticleNotFoundException;
import com.loga.logisticservice.service.ISupplyService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/logistic-service")
public class SupplyController {

    private final ISupplyService supplyService;
    private final ReportServiceProxy reportServiceProxy;

    @Autowired
    public SupplyController(ISupplyService supplyService,
                            ReportServiceProxy reportServiceProxy
    ) {
        this.supplyService = supplyService;
        this.reportServiceProxy = reportServiceProxy;
    }

    @PostMapping(path = "/furnishers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Furnisher> registrateFurnisher(@RequestBody Furnisher furnisher){
        Furnisher created = supplyService.registrateFurnisher(furnisher);
        return ResponseEntity.ok(created);
    }

    @GetMapping(path = "/furnishers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Furnisher>> readFurnisher(){
        List<Furnisher> furnishers = supplyService.listFurnisher();
        return ResponseEntity.ok(furnishers);
    }

    @GetMapping(path = "/furnishers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Furnisher> readFurnisher(@PathVariable Long id){
        Furnisher furnisher = supplyService.readFurnisher(id);
        return ResponseEntity.ok(furnisher);
    }

    @PutMapping(path = "/furnishers/{id}")
    public void editFurnisher(@RequestBody Furnisher furnisher, @PathVariable Long id){
        supplyService.editFurnisher(furnisher,id);
    }

    @DeleteMapping(path = "/furnishers/{id}")
    public void deleteFurnisher(@PathVariable Long id){
        supplyService.deleteFurnisher(id);
    }

    @PostMapping(path = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> registrateOrder(@RequestBody Order order){
        Order created = supplyService.registrateOrder(order);
        return ResponseEntity.ok(created);
    }

    @GetMapping(path = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> readOrders(){
        List<Order> orders = supplyService.listOrder();
        return ResponseEntity.ok(orders);
    }

    @GetMapping(path = "/orders/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> readOrders(@PathVariable Long id){
        Order order = supplyService.readOrder(id);
        return ResponseEntity.ok(order);
    }

    @PutMapping(path = "/orders/{id}")
    public void editOrder(@RequestBody Order order, @PathVariable Long id){
        supplyService.editOrder(order,id);
    }

    @DeleteMapping(path = "/orders/{id}")
    public void deleteOrder(@PathVariable Long id){
        supplyService.deleteOrder(id);
    }

    @PutMapping(path = "/orders/{id}/delivery")
    public void addDelivery(@RequestBody Delivery delivery, @PathVariable Long id){
        supplyService.editOrder(delivery,id);
    }

    @DeleteMapping(path = "/orders/delivery/{id}")
    public void deleteDelivery(@PathVariable Long id){
        supplyService.deleteDelivery(id);
    }

    @PutMapping(path = "/orders/{id}/article")
    public void addArticle(@RequestBody Article article, @PathVariable Long id){
        supplyService.editOrder(article,id);
    }

    @GetMapping(path = "/orders/order/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void printOrder(HttpServletResponse response, @PathVariable Long id){
        reportServiceProxy.produceOrderReport(response,id);
    }

    @GetMapping(path = "/deliveries/delivery/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void printDelivery(HttpServletResponse response, @PathVariable Long id){
        reportServiceProxy.produceDeliveryReport(response,id);
    }
}
