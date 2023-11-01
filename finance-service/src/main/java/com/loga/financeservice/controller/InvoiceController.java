package com.loga.financeservice.controller;

import com.loga.financeservice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/finance-service")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping(path = "/invoicing/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public void invoicing(Long id){
        try {
            invoiceService.invoicing(id);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(path = "/invoicing/{reference}",produces = MediaType.APPLICATION_JSON_VALUE)
    public void invoicing(String reference){
        try {
            invoiceService.invoicing(reference);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
