package com.maybank.assignment.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class ThirdPartyApiClient {

    private final WebClient webClient = WebClient.builder()
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    public Object callApi(String url, Map<String, String> params) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(url)
                        .queryParam("query", params.get("query"))
                        .build())
                .retrieve()
                .bodyToMono(Object.class)
                .block(); // Blocking call for simplicity (can be removed for async)
    }
}
