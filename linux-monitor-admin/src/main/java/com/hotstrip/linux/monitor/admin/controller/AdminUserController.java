package com.hotstrip.linux.monitor.admin.controller;

import com.hotstrip.linux.monitor.common.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin-user")
public class AdminUserController {

    @GetMapping("/list")
    public Result getAdminUserList() {
        return Result.success("success");
    }
}
