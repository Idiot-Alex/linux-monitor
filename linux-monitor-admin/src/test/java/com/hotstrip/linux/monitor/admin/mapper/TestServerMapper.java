package com.hotstrip.linux.monitor.admin.mapper;

import com.hotstrip.linux.monitor.admin.TestApplication;
import com.hotstrip.linux.monitor.admin.pojo.entity.ServerDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Slf4j
public class TestServerMapper extends TestApplication {

    @Resource
    private ServerMapper serverMapper;

    private ServerDO initServerDO() {
        return ServerDO.builder()
                .id(1)
                .host("127.0.0.1")
                .user("user-name")
                .password("password")
                .port(22)
                .createTime(new Timestamp(System.currentTimeMillis()))
                .updateTime(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    @Before
    public void setup() {
        ServerDO serverDO = initServerDO();
        int rows = serverMapper.insert(serverDO);
        Assert.assertEquals(1, rows);
    }

    @Test
    public void testFindById() {
        ServerDO serverDO = initServerDO();
        ServerDO data = serverMapper.findById(serverDO.getId());
        Assert.assertEquals(serverDO.getId(), data.getId());
    }
}
