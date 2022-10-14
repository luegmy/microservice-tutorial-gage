package com.tutorial.gatewayservice.controller;

import reactor.core.publisher.Mono;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;

@RestController
public class GatewatController {

    @GetMapping("/")
    public Mono<String>index(WebSession session){
        return Mono.just(session.getId());
    }

    @GetMapping("/token")
    public Mono<String> getToken(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client){
        return Mono.just(client.getAccessToken().getTokenValue());
    }
}
