package com.loga.financeservice.app.api;

import com.loga.financeservice.app.factory.Repair;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("gateway-server")
public interface MaintenanceServiceProxy {

    @GetMapping(value = "/maintenance-service/repairs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Repair retrieveRepair(@PathVariable("id") Long id) ;

    @GetMapping(value = "/maintenance-service/repairs/reference/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    Repair retrieveRepair(@PathVariable("reference") String reference) ;
}
