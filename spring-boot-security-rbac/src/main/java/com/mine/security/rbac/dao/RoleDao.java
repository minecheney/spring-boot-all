package com.mine.security.rbac.dao;

import com.mine.security.rbac.po.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: mich
 * @Date: 2020/2/18 15:27
 */
public interface RoleDao {
    List<Role> selectByUserId(@Param("id") Long id);
}
