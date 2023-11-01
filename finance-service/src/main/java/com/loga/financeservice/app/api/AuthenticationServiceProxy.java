package com.loga.financeservice.app.api;

import com.loga.financeservice.app.factory.AuthSession;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.MediaType;

@FeignClient("gateway-server")
public interface AuthenticationServiceProxy {
    @GetMapping(path = "/authenticaion-service/session/{token}",produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthSession checkSession(@PathVariable String token);
}
