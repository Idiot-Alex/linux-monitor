package com.hotstrip.linux.monitor.admin.service;

import com.hotstrip.linux.monitor.admin.TestApplication;
import com.hotstrip.linux.monitor.admin.pojo.entity.ServerDO;
import com.hotstrip.linux.monitor.admin.mapper.ServerMapper;
import com.hotstrip.linux.monitor.admin.pojo.vo.ServerVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TestServerService extends TestApplication {
    @Resource
    private ServerService serverService;
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
        serverMapper.insert(serverDO);
        ServerVO serverVO = serverService.findById(1);
        Assert.assertEquals(serverDO.getId(), serverVO.getId());
    }

    @Test
    public void testDelete() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        serverService.delete(ids);
        ServerVO serverVO = serverService.findById(1);
        Assert.assertNull(serverVO);
    }
}
