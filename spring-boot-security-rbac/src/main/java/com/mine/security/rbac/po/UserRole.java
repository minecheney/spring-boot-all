package com.mine.security.rbac.po;

import lombok.Data;

@Data
public class UserRole {
    /**
     * 主键
     */
    private Long id;
    private Long userId;
    private Long roleId;
}
