package com.loga.logisticservice.app.api;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("gateway-server")
public interface ReportServiceProxy {
    @GetMapping(path = "/report-service/orders/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    void produceOrderReport(HttpServletResponse response, @PathVariable Long id);

    @GetMapping(path = "/report-service/deliveries/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    void produceDeliveryReport(HttpServletResponse response, @PathVariable Long id);
}
