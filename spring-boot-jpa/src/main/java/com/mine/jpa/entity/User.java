package com.mine.jpa.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Accessors(chain = true) //运行链式操作 set返回this
@Entity
@Table(name = "orm_user")
public class User implements Serializable {
    private static final long serialVersionUID = 2850115226972281775L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "salt")
    private String salt;
    private String email;
    private String phone_number;
    private String status;
    private String create_time;
//    private String lastLoginTime;
//    private String lastUpdateTime;

}
