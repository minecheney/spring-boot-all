package com.mine.security.rbac.dao;

import com.mine.security.rbac.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Author: mich
 * @Date: 2020/2/18 15:27
 */
public interface UserDao {
    User findByUsernameOrEmailOrPhone(@Param("username") String username, @Param("email") String email, @Param("phone") String phone);

    List<User> findByUsernameIn(@Param("usernameList") List<String> usernameList);
}
