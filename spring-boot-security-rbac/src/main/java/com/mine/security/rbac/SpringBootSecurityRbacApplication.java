package com.mine.security.rbac;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mine.security.rbac.dao")
public class SpringBootSecurityRbacApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityRbacApplication.class, args);
    }

}
