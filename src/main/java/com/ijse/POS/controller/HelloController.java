package com.ijse.POS.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HelloController {
    
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello SpringBoot";
    }

    @PostMapping("/hello")
    public String writeHello(){
        return "Hello SpringBoot Write";
    }


}
