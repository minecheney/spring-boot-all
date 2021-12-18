package com.mine.security.jwt.service;

import com.mine.security.jwt.vo.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author: mich
 * @Date: 2020/2/18 15:26
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmailOrPhone) throws UsernameNotFoundException {
//        User user = userDao.findByUsernameOrEmailOrPhone(usernameOrEmailOrPhone, usernameOrEmailOrPhone, usernameOrEmailOrPhone);
//        if (user == null) {
//            throw new UsernameNotFoundException("未找到用户信息 : " + usernameOrEmailOrPhone);
//        }
//        List<Role> roles = roleDao.selectByUserId(user.getId());
//        List<Long> roleIds = roles.stream()
//                .map(Role::getId)
//                .collect(Collectors.toList());
//        List<Permission> permissions = permissionDao.selectByRoleIdList(roleIds);
        return new UserPrincipal(1, passwordEncoder.encode("123456"), 1);
    }
}
