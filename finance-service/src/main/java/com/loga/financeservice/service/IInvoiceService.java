package com.loga.financeservice.service;

import com.loga.financeservice.entity.Invoice;
import org.springframework.cloud.openfeign.FeignClient;

import java.io.IOException;

public interface IInvoiceService {
    Invoice create(Invoice invoice);
    void invoicing(Long id) throws IOException, InterruptedException;
    void invoicing(String reference) throws IOException, InterruptedException;
}
