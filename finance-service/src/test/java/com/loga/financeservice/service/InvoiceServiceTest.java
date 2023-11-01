package com.loga.financeservice.service;

import com.loga.financeservice.entity.Invoice;
import com.loga.financeservice.entity.Item;
import com.loga.financeservice.entity.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InvoiceServiceTest {

    @Autowired
    private IInvoiceService invoiceService;

    @Test
    void invoicing() {
        Invoice invoice = new Invoice();
        invoice.setCreatedAt(new Date());
        invoice.setDossier("DOSSIER001");
        invoice.setReference("FACTUREVENTE");
        invoice.addItem(new Item(null,"dkd",1F,10000,"E"));
        invoice.addPayment(new Payment(null,new Date(),"especes","premier paiement",10000));

        Invoice created = invoiceService.create(invoice);
        System.out.println(created);
        assertNotNull(created,"Failed");
    }
}