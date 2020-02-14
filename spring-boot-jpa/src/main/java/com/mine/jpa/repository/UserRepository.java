package com.mine.jpa.repository;

import com.mine.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: mich
 * @Date: 2020/2/14 23:14
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
