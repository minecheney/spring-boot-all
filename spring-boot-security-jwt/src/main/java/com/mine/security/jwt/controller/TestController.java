package com.mine.security.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @Author mineChen
 * @Date 2021/5/4 下午 03:42
 */
@RequestMapping("/api/test")
@RestController
public class TestController {

    @GetMapping
    public String test(){
        return "Test";
    }
}
