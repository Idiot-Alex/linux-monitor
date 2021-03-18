package com.hotstrip.linux.monitor.admin.service;

import com.hotstrip.linux.monitor.admin.TestApplication;
import com.hotstrip.linux.monitor.admin.page.PageParams;
import com.hotstrip.linux.monitor.admin.page.PageResult;
import com.hotstrip.linux.monitor.admin.pojo.query.AdminUserQuery;
import com.hotstrip.linux.monitor.admin.pojo.vo.AdminUserVO;
import com.hotstrip.linux.monitor.common.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

@Slf4j
public class TestAdminUserService extends TestApplication {
    @Resource
    private AdminUserService adminUserService;

    @Test
    public void testListByPage() {
        AdminUserQuery adminUserQuery = AdminUserQuery.builder()
                .pageParams(new PageParams())
                .build();
        PageResult<AdminUserVO> pageResult = adminUserService.listByPage(adminUserQuery);
        log.info(JacksonUtil.objectToJsonString(pageResult));
        Assert.assertEquals(1, pageResult.getPageParams().getTotalCount());
    }
}
