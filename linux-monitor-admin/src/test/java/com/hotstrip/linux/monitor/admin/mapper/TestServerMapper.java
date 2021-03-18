package com.hotstrip.linux.monitor.admin.mapper;

import com.hotstrip.linux.monitor.admin.TestApplication;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

@Slf4j
public class TestServerMapper extends TestApplication {

    @Resource
    private ServerMapper serverMapper;

}
