package com.hotstrip.linux.monitor.admin.mapper;

import com.hotstrip.linux.monitor.admin.TestApplication;
import com.hotstrip.linux.monitor.admin.pojo.entity.AdminUserDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

@Slf4j
public class TestAdminUserMapper extends TestApplication {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Test
    public void testFindById() {
        Integer id = 1;
        AdminUserDO data = adminUserMapper.findById(id);
        Assert.assertEquals(id, data.getId());
    }
}
