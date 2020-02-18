package com.mine.security.rbac.controller;

import cn.hutool.core.collection.CollUtil;
import com.mine.security.rbac.common.ApiResponse;
import com.mine.security.rbac.common.PageResult;
import com.mine.security.rbac.common.Status;
import com.mine.security.rbac.exception.SecurityException;
import com.mine.security.rbac.payload.PageCondition;
import com.mine.security.rbac.service.MonitorService;
import com.mine.security.rbac.utils.PageUtil;
import com.mine.security.rbac.utils.SecurityUtil;
import com.mine.security.rbac.vo.OnlineUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 监控 Controller，在线用户，手动踢出用户等功能
 */
@Slf4j
@RestController
@RequestMapping("/api/monitor")
public class MonitorController {
    @Autowired
    private MonitorService monitorService;

    /**
     * 在线用户列表
     *
     * @param pageCondition 分页参数
     */
    @GetMapping("/online/user")
    public ApiResponse onlineUser(PageCondition pageCondition) {
        PageUtil.checkPageCondition(pageCondition, PageCondition.class);
        PageResult<OnlineUser> pageResult = monitorService.onlineUser(pageCondition);
        return ApiResponse.ofSuccess(pageResult);
    }

    /**
     * 批量踢出在线用户
     *
     * @param names 用户名列表
     */
    @DeleteMapping("/online/user/kickout")
    public ApiResponse kickoutOnlineUser(@RequestBody List<String> names) {
        if (CollUtil.isEmpty(names)) {
            throw new SecurityException(Status.PARAM_NOT_NULL);
        }
        if (names.contains(SecurityUtil.getCurrentUsername())){
            throw new SecurityException(Status.KICKOUT_SELF);
        }
        monitorService.kickout(names);
        return ApiResponse.ofSuccess();
    }
}
