package com.vti.api_gateway.filters;

import com.vti.api_gateway.exception.ValidationException;
import com.vti.api_gateway.response.VerifyTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter implements GatewayFilter {

    private final RestClient restClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        VerifyTokenResponse response = restClient.get()
                .uri("http://auth-service:8082/api/v1/auth/verify")
                .header("Authorization", authHeader)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        if (response != null) {
            throw new ValidationException(HttpStatus.UNAUTHORIZED, "Response from auth service invalid");
        }

        Integer status = response.getStatus();
        String message = response.getMessage();
        String xUserToken = response.getXUserToken();

        if (status == null || status != HttpStatus.OK.value() || !message.equals("Success") || !StringUtils.hasText(xUserToken)) {
            throw new ValidationException(HttpStatus.UNAUTHORIZED, "Invalid token or user information");
        }

        return null;
    }

    private void populateRequestWithHeader(ServerWebExchange exchange, String xUserToken) {
        exchange.getRequest().mutate()
                .header("X-User_Token", xUserToken)
                .build();
    }
}
