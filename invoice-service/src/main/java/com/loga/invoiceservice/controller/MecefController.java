package com.loga.invoiceservice.controller;

import com.loga.invoiceservice.app.factory.*;
import com.loga.invoiceservice.exception.ResourceNotFoundException;
import com.loga.invoiceservice.service.IMeCEF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/invoice-service")
public class MecefController {

    @Autowired
    private IMeCEF mecef;

    @GetMapping(path = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public InfoResponseDto info() {
        try {
            return mecef.info();
        } catch (IOException | InterruptedException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @GetMapping(path = "/taxes", produces = MediaType.APPLICATION_JSON_VALUE)
    public TaxGroupDto taxes() {
        try {
            return mecef.taxes();
        } catch (IOException | InterruptedException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @GetMapping(path = "/invoices", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InvoiceTypeDto> invoices() {
        try {
            return mecef.invoices();
        } catch (IOException | InterruptedException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @GetMapping(path = "/payments", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PaymentTypeDto> payments(){
        try {
            return mecef.payments();
        } catch (IOException | InterruptedException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @GetMapping(path = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusResponseDto status(){
        try {
            return mecef.status();
        } catch (IOException | InterruptedException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @PostMapping(path = "/invoicing", produces = MediaType.APPLICATION_JSON_VALUE)
    public InvoiceResponseDataDto invoicing(InvoiceRequestDataDto invoice){
        try {
            return mecef.invoicing(invoice);
        } catch (IOException | InterruptedException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @PutMapping(path = "/confirmation/{uid}/{action}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SecurityElementsDto confirmation(@PathVariable String uid,@PathVariable String action){
        try {
            return mecef.confirmation(uid,action);
        } catch (IOException | InterruptedException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @GetMapping(path = "/details/{uid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public InvoiceDetailsDto details(@PathVariable String uid){
        try {
            return mecef.details(uid);
        } catch (IOException | InterruptedException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
