package com.github.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HelloController {

    @GetMapping("/hello")
    public String helloAnonymous(){
        return "hello";
    }

    @GetMapping("/hello2")
    public String loginHello(){
        return "hello2";
    }

    @GetMapping("/hello3")
    @PreAuthorize("hasAuthority('system:dept:list')")
    public String helloWithAuth(){
        return "hello3";
    }

    @GetMapping("/hello4")
    @PreAuthorize("@exp.hasAuthority('system:dept:list')") //使用自定义权限校验方法 ==> 格式：@BeanName.MethodName()
    public String helloWithCustomAuth(){
        return "hello4";
    }
}
