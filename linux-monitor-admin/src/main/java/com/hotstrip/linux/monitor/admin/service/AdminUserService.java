package com.hotstrip.linux.monitor.admin.service;

import com.hotstrip.linux.monitor.admin.dto.AdminUserDTO;

public interface AdminUserService {

    void insertOrUpdate(AdminUserDTO adminUserDTO);
}
