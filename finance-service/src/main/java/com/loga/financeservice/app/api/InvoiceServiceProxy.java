package com.loga.financeservice.app.api;

import com.loga.financeservice.app.factory.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.io.IOException;
import java.util.List;

@FeignClient("gateway-server")
public interface InvoiceServiceProxy {

    @PostMapping(path = "/invoice-service/invoicing",produces = MediaType.APPLICATION_JSON_VALUE)
    InvoiceResponseDataDto invoicing(InvoiceRequestDataDto invoice);

    @PutMapping(path = "/invoice-service/confirmation/{uid}/{action}", produces = MediaType.APPLICATION_JSON_VALUE)
    SecurityElementsDto confirmation(@PathVariable String uid, @PathVariable String action);

    @GetMapping(path = "/invoice-service/details/{uid}", produces = MediaType.APPLICATION_JSON_VALUE)
    InvoiceDetailsDto details(@PathVariable String uid);
}
