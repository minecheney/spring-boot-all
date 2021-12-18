package com.example.mybatisplus.entity;

import lombok.Data;

/**
 * User
 *
 * @Author mineChen
 * @Date 2021/12/18 上午 10:05
 */
@Data
public class User {
    private Integer id;
    private String userName;
    private Integer type;
    private String email;
    private String mobile;
}
