package com.mine.security.jwt.controller;

import com.alibaba.fastjson.JSON;
import com.mine.security.jwt.enums.JwtRedisEnum;
import com.mine.security.jwt.jwt.util.JwtTokenUtil;
import com.mine.security.jwt.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 认证 Controller，包括用户注册，用户登录请求
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 登录
     */
    @PostMapping("/login")
    public String login() {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("name", "123456"));

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        final String randomKey = jwtTokenUtil.getRandomKey();
//        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        String username = "name";
        final String token = jwtTokenUtil.generateToken(username, randomKey);


        // 判断是否开启允许多人同账号同时在线，若不允许的话则先删除之前的
        if (securityProperties.getJwt().isPreventsLogin()) {
            // T掉同账号已登录的用户token信息
            batchDel(JwtRedisEnum.getTokenKey(username, "*"));
            // 删除同账号已登录的用户认证信息
            batchDel(JwtRedisEnum.getAuthenticationKey(username, "*"));
        }

        // 存到redis
        redisTemplate.opsForValue().set(JwtRedisEnum.getTokenKey(username, randomKey),
                token,
                securityProperties.getJwt().getExpiration(),
                TimeUnit.SECONDS);

        // 将认证信息存到redis，方便后期业务用，也方便 JwtAuthenticationTokenFilter用
        redisTemplate.opsForValue().set(JwtRedisEnum.getAuthenticationKey(username, randomKey),
                JSON.toJSONString(authentication),
                securityProperties.getJwt().getExpiration(),
                TimeUnit.SECONDS
        );
        return token;
    }

    /**
     * 批量删除redis的key
     *
     * @param key：redis-key
     */
    private void batchDel(String key){
        Set<String> set = redisTemplate.keys(key);
        Iterator<String> it = set.iterator();
        while(it.hasNext()){
            String keyStr = it.next();
            redisTemplate.delete(keyStr);
        }
    }
}
