package com.maybank.assignment.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/V1/maybank/assignment")
public class WelcomeController {

    @Value("${welcome.message}")
    private String greetingMessage;

    @GetMapping("/welcome")
    public String helloMessage(){
        return greetingMessage;
    }
}
