package com.maybank.assignment.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ThirdPartyApiService {

    private final RestTemplate restTemplate;

    public ThirdPartyApiService() {
        this.restTemplate = new RestTemplate();
    }

    @Value("${third.party.endpoint}")
    private String thirdPartyEndpoint;

    public String fetchThirdPartyData(String id) {

        String thirdPartyApiUrl = thirdPartyEndpoint + id;

        log.info("URL of the third-party API{}::", thirdPartyApiUrl);

        String response = restTemplate.getForObject(thirdPartyApiUrl, String.class);
        log.info("Response from Endpoint{}", response);

        // Return the response
        return response;
    }
}

