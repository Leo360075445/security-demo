package com.github.controller;

import com.github.service.IUserService;
import com.github.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/selectAll")
    public JsonResult<Object> selectAll(){
        return JsonResult.ok(userService.selectAllUsers());
    }

}
