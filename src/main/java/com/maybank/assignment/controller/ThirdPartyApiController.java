package com.maybank.assignment.controller;

import com.maybank.assignment.service.impl.ThirdPartyApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/call-third-party-api")
public class ThirdPartyApiController {

    @Autowired
    private ThirdPartyApiService thirdPartyApiService;

    // API Endpoint
    @GetMapping("/assignment/{postId}")
    public ResponseEntity<?> getThirdPartyData(@PathVariable String postId) {
        String response = thirdPartyApiService.fetchThirdPartyData(postId);
        return ResponseEntity.ok(response);
    }
}