package com.loga.logisticservice.app.api;

import com.loga.logisticservice.app.factory.AuthSession;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("gateway-server")
public interface AuthenticationServiceProxy {
    @GetMapping(path = "/authentication-service/session/{token}",produces = MediaType.APPLICATION_JSON_VALUE)
    AuthSession checkSession(@PathVariable String token);
}
