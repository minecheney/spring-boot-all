package com.example.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.entity.User;
import com.example.mybatisplus.mapper.UserMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
//        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName", "admin");
        final User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }
}
