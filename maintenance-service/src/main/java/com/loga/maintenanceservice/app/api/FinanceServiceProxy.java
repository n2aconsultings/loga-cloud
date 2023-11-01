package com.loga.maintenanceservice.app.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("gateway-server")
public interface FinanceServiceProxy {

    @GetMapping(path = "/finance-service/resolve/{words}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Object> invoice(@PathVariable String words);
}
