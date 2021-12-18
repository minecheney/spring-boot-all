package com.mine.mybatis.service.impl;

import com.mine.data.dao.UserDao;
import com.mine.data.po.User;
import com.mine.mybatis.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: mich
 * @Date: 2020/2/6 13:20
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Override
    public List<User> getUser() {
        return userDao.getUser();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update() {
        final int update = userDao.update();
        if (update==1){
//            throw new RuntimeException("事务失败");
        }
        return update;
    }
}
