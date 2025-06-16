package com.vti.api_gateway.config;

import com.vti.api_gateway.filters.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {
    private final AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("account-service", r -> r.path("/api/v1/accounts/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://account-service"))
                .route("department-service", r -> r.path("/api/v1/departments/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://department-service"))
                .route("auth-service", r -> r.path("/api/v1/auth/**")
                        .uri("lb://auth-service"))
                .build();
    }

}
