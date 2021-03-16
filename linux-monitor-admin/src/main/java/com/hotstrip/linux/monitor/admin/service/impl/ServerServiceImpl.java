package com.hotstrip.linux.monitor.admin.service.impl;

import com.hotstrip.linux.monitor.admin.mapper.ServerMapper;
import com.hotstrip.linux.monitor.admin.service.ServerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ServerServiceImpl implements ServerService {
    @Resource
    private ServerMapper serverMapper;

    @Override
    public void insert() {

    }
}
