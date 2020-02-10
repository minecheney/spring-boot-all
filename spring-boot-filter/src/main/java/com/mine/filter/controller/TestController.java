package com.mine.filter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: mich
 * @Date: 2020/2/10 21:19
 */
@RestController
@RequestMapping("/user")
public class TestController {
    @GetMapping("/{id:\\d+}")
    public String test(@PathVariable String id){
        return id;
    }
}
