package com.hotstrip.linux.monitor.admin.controller;

import com.github.pagehelper.Page;
import com.hotstrip.linux.monitor.admin.page.PageResult;
import com.hotstrip.linux.monitor.admin.pojo.query.AdminUserQuery;
import com.hotstrip.linux.monitor.admin.page.PageParams;
import com.hotstrip.linux.monitor.admin.pojo.vo.AdminUserVO;
import com.hotstrip.linux.monitor.admin.service.AdminUserService;
import com.hotstrip.linux.monitor.common.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin-user")
public class AdminUserController {

    @Resource
    private AdminUserService adminUserService;

    @GetMapping("/list")
    public Result getAdminUserList(final Integer pageNo,
                                   final Integer pageSize,
                                   final String user) {
        AdminUserQuery adminUserQuery = AdminUserQuery.builder()
                .pageParams(new PageParams(pageNo, pageSize))
                .user(user)
                .build();
        PageResult<AdminUserVO> pageResult = adminUserService.listByPage(adminUserQuery);
        return Result.success("success", pageResult);
    }
}
