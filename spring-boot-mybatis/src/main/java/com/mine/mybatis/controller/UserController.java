package com.mine.mybatis.controller;

import com.mine.data.common.CommonResult;
import com.mine.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/list")
    public CommonResult getUser(){
        return CommonResult.success(userService.getUser());
    }
    @GetMapping("/update")
    public CommonResult updateUser(){
        return CommonResult.success(userService.update());
    }
}
