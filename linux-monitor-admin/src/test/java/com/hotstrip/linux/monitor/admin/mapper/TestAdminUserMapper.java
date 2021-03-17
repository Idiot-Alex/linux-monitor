package com.hotstrip.linux.monitor.admin.mapper;

import com.hotstrip.linux.monitor.admin.TestApplication;
import com.hotstrip.linux.monitor.admin.pojo.entity.AdminUserDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Slf4j
public class TestAdminUserMapper extends TestApplication {

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
        int rows = adminUserMapper.insert(adminUserDO);
        Assert.assertEquals(1, rows);
    }

    @Test
    public void testFindById() {
        AdminUserDO adminUserDO = initAdminUserDO();
        AdminUserDO data = adminUserMapper.findById(adminUserDO.getId());
        Assert.assertEquals(adminUserDO.getId(), data.getId());
    }
}
