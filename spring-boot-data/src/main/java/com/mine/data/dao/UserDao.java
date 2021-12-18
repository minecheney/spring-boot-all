package com.mine.data.dao;

import com.mine.data.po.User;

import java.util.List;

public interface UserDao {
    List<User> getUser();

    int update();

}
