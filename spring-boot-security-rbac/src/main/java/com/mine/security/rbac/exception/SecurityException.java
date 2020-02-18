package com.mine.security.rbac.exception;

import com.mine.security.rbac.common.BaseException;
import com.mine.security.rbac.common.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 全局异常
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SecurityException extends BaseException {
    public SecurityException(Status status) {
        super(status);
    }

    public SecurityException(Status status, Object data) {
        super(status, data);
    }

    public SecurityException(Integer code, String message) {
        super(code, message);
    }

    public SecurityException(Integer code, String message, Object data) {
        super(code, message, data);
    }
}
