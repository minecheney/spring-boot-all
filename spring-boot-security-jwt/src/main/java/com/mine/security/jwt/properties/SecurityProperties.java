package com.mine.security.jwt.properties;

import com.mine.security.jwt.properties.jwt.JwtProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * APP模块的统一配置
 */
@ConfigurationProperties(prefix = "com.chentongwei.security")
public class SecurityProperties {

    private JwtProperties jwt = new JwtProperties();

    public JwtProperties getJwt() {
        return jwt;
    }

    public void setJwt(JwtProperties jwt) {
        this.jwt = jwt;
    }

}
