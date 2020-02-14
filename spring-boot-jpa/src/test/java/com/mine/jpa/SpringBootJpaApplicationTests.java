package com.mine.jpa;

import com.mine.jpa.entity.User;
import com.mine.jpa.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class SpringBootJpaApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
        Optional<User> user = userRepository.findById(1L);
        System.out.println(user.get());
    }

}
