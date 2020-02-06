package com.mine.data.po;

import lombok.Data;

@Data
public class User {
    private String id;
    private String name;
    private String password;
    private String salt;
    private String email;
    private String phone_number;
    private String status;
    private String create_time;
    private String lastLoginTime;
    private String lastUpdateTime;

}
