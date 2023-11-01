package com.loga.logisticservice.app.api;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("gateway-server")
public interface FinanceServiceProxy {
    @GetMapping(path = "/finance-service/invoice/sale/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    void invoiceSale(HttpServletResponse response, @PathVariable Long id);
}
