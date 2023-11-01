package com.loga.logisticservice.app.api;

import com.loga.logisticservice.app.factory.Repair;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("gateway-server")
public interface MaintenanceServiceProxy {
    @GetMapping(path = "/maintenance-service/repairs/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    Repair retrieveRepair(@PathVariable Long id);

    @GetMapping(path = "/maintenance-service/repairs/reference/{reference}",produces = MediaType.APPLICATION_JSON_VALUE)
    Repair retrieveRepair(@PathVariable String reference);
}
