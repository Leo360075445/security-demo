package com.github.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello2")
    public String helloWithoutAuth(){
        return "hello2";
    }

    @GetMapping("/hello3")
    @PreAuthorize("hasAuthority('system:dept:list')")
    public String helloWithAuth(){
        return "hello3";
    }
}
