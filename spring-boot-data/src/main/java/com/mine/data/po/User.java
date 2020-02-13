package com.mine.data.po;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true) //运行链式操作 set返回this
public class User implements Serializable {
    private static final long serialVersionUID = 2850115226972281775L;
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
