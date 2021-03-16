package com.hotstrip.linux.monitor.admin.service.impl;

import com.hotstrip.linux.monitor.admin.dto.AdminUserDTO;
import com.hotstrip.linux.monitor.admin.entity.AdminUserDO;
import com.hotstrip.linux.monitor.admin.mapper.AdminUserMapper;
import com.hotstrip.linux.monitor.admin.service.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AdminUserServiceImpl implements AdminUserService {
    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public void insertOrUpdate(final AdminUserDTO adminUserDTO) {
        AdminUserDO adminUserDO = AdminUserDO.buildDO(adminUserDTO);
        if (adminUserDO.getId() == null) {
            adminUserMapper.insert(adminUserDO);
        } else {
            adminUserMapper.update(adminUserDO);
        }
    }
}
