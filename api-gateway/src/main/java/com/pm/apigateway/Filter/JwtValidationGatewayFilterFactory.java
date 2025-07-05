package com.pm.apigateway.Filter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class JwtValidationGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    private WebClient webClient;

    public JwtValidationGatewayFilterFactory(WebClient.Builder webClientBuilder ,
                                             @Value("${auth.service.url}") String authServiceUrl) {
        this.webClient = webClientBuilder.baseUrl(authServiceUrl).build();
    }

    @Override
    public GatewayFilter apply(Object config){
        return (exchange, chain) -> {

            String token = exchange.getRequest()
                                   .getHeaders()
                                   .getFirst(HttpHeaders.AUTHORIZATION);

            if (token == null || !token.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            return webClient.get()
                            .uri("/validate")
                            .header(HttpHeaders.AUTHORIZATION, token)
                            .retrieve()
                            .toBodilessEntity()
                            .then(chain.filter(exchange));
        };
    }
}