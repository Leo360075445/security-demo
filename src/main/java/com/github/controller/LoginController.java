package com.github.controller;

import com.github.domain.User;
import com.github.service.ILoginService;
import com.github.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sso")
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @PostMapping("/login")
    public JsonResult login(@RequestBody User user){
        return loginService.login(user);
    }

    @GetMapping("/logout")
    public JsonResult logout(){
        return loginService.logout();
    }
}
