package com.github.controller;

import com.github.domain.User;
import com.github.service.ILoginService;
import com.github.service.IUserService;
import com.github.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private ILoginService loginService;

    @GetMapping("/selectAll")
    public JsonResult<Object> selectAll(){
        return JsonResult.ok(userService.selectAllUsers());
    }

    @PostMapping("/login")
    public JsonResult login(@RequestBody User user){
        return loginService.login(user);
    }

    @GetMapping("/test3")
    public void test3(){}
}
