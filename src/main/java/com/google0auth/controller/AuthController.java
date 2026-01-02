package com.google0auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @GetMapping("/public")
    public String method1() {
         return "this is the public method1";
    }
    @GetMapping("/private")
    public String method2() {
            return "this is the public method2";
    }
}
