package com.loga.financeservice.app.api;

import com.loga.financeservice.app.factory.Dossier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("gateway-server")
public interface CustomerServiceProxy {
    @GetMapping(path = "/customer-service/dossiers/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Dossier retrieveDossier(@PathVariable Long id);

    @GetMapping(path = "/customer-service/dossiers/reference/{reference}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Dossier retrieveDossier(@PathVariable String reference);
}
