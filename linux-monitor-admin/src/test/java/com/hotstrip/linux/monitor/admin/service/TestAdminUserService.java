package com.hotstrip.linux.monitor.admin.service;

import com.hotstrip.linux.monitor.admin.TestApplication;
import com.hotstrip.linux.monitor.admin.pojo.dto.AdminUserDTO;
import com.hotstrip.linux.monitor.admin.pojo.entity.AdminUserDO;
import com.hotstrip.linux.monitor.admin.mapper.AdminUserMapper;
import com.hotstrip.linux.monitor.admin.pojo.vo.AdminUserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TestAdminUserService extends TestApplication {
    @Resource
    private AdminUserService adminUserService;
    @Resource
    private AdminUserMapper adminUserMapper;

    private AdminUserDO initAdminUserDO() {
        return AdminUserDO.builder()
                .id(1)
                .userName("user-name")
                .password("password")
                .createTime(new Timestamp(System.currentTimeMillis()))
                .updateTime(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    @Before
    public void setup() {
        AdminUserDO adminUserDO = initAdminUserDO();
        adminUserMapper.insert(adminUserDO);
        AdminUserVO adminUserVO = adminUserService.findById(1);
        Assert.assertEquals(adminUserDO.getId(), adminUserVO.getId());
    }

    @Test
    public void testDelete() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        adminUserService.delete(ids);
        AdminUserVO adminUserVO = adminUserService.findById(1);
        Assert.assertNull(adminUserVO);
    }
}
