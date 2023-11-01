package com.loga.logisticservice.controller;

import com.loga.logisticservice.entity.Product;
import com.loga.logisticservice.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/logistic-service")
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> registrate(@RequestBody Product product){
        Product created = productService.create(product);
        return ResponseEntity.ok(created);
    }

    @GetMapping(path = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> read(@PathVariable Long id){
        Product product = productService.read(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> read(){
        List<Product> products = productService.list();
        return ResponseEntity.ok(products);
    }

    @GetMapping(path = "/products/{reference}")
    public ResponseEntity<List<Product>> search(@PathVariable String reference){
        List<Product> products = productService.list(reference);
        return ResponseEntity.ok(products);
    }

    @PutMapping(path = "/products/{id}")
    public void edit(@RequestBody Product product, @PathVariable Long id){
        productService.edit(product,id);
    }

    @DeleteMapping(path = "/products/{id}")
    public void delete(@PathVariable Long id){
        //todo : delete product
    }
}
