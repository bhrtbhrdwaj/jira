package com.jira.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

//@RestController("/")
public class Test {
    @Autowired
    private final WebClient.Builder webClientBuilder;

    public Test(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @GetMapping
    public String getTest() {
        return
                webClientBuilder.baseUrl("https://api.nationalize.i")
                        .build()
                        .get()
                        .uri(uriBuilder ->
                                uriBuilder.path("/")
                                        .queryParam("name", "nathaniel")
                                        .build())
                        .retrieve()
                        /*.onStatus(HttpStatus::isError,
                            response -> response.bodyToMono(String.class).flatMap(error -> Mono.error(new RuntimeException(error))))
                        */
                        .bodyToMono(String.class)
                        .onErrorReturn("default response")
                        .block();
    }
}
