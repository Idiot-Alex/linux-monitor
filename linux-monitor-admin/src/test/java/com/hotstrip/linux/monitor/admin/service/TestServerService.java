package com.hotstrip.linux.monitor.admin.service;

import com.hotstrip.linux.monitor.admin.TestApplication;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

@Slf4j
public class TestServerService extends TestApplication {
    @Resource
    private ServerService serverService;

}
