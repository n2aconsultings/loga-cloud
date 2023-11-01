package com.loga.gatewayserver.vendor.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class Router {

    public static final List<String> openApiEndpoints = List.of(
            "/authentication-service/**",
            "/customer-service/**",
            "/maintenance-service/**",
            "/reporting-service/**",
            "/finance-service/**",
            "/intelligent-service/**",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured =
            serverHttpRequest -> openApiEndpoints.stream()
                    .noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));
}
