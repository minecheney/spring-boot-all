package com.mine.security.jwt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DemoController
 *
 * @Author mineChen
 * @Date 2021/5/4 下午 05:03
 */
@Slf4j
@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @GetMapping
    public String test(){
        return "Demo";
    }
}
