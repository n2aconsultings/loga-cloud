package com.loga.invoiceservice.service;

import com.loga.invoiceservice.app.factory.*;

import java.io.IOException;
import java.util.List;

public interface IMeCEF {

    InfoResponseDto info() throws IOException, InterruptedException;

    TaxGroupDto taxes() throws IOException, InterruptedException;

    List<InvoiceTypeDto> invoices() throws IOException, InterruptedException;

    List<PaymentTypeDto> payments() throws IOException, InterruptedException;

    StatusResponseDto status() throws IOException, InterruptedException;

    InvoiceResponseDataDto invoicing(InvoiceRequestDataDto data) throws IOException, InterruptedException;

    SecurityElementsDto confirmation(String uid, String action) throws IOException, InterruptedException;

    InvoiceDetailsDto details(String uid) throws IOException, InterruptedException;
}
