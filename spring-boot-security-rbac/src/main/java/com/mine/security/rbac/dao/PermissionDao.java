package com.mine.security.rbac.dao;

import com.mine.security.rbac.po.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: mich
 * @Date: 2020/2/18 15:27
 */
public interface PermissionDao {
    List<Permission> selectByRoleIdList(@Param("roleIds") List<Long> roleIds);
}
