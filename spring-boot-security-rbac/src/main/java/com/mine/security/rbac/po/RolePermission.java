package com.mine.security.rbac.po;

import lombok.Data;

@Data
public class RolePermission {
    /**
     * 主键
     */
    private Long id;
    private Long roleId;
    private Long permissionId;
}
