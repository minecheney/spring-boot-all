package com.mine.mybatis.controller;

import com.mine.data.common.CommonResult;
import com.mine.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/user")
    public CommonResult getUser(){
        return CommonResult.success(userService.getUser());
    }
}
