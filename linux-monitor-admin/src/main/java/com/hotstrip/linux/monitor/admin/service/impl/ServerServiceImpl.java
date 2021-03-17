package com.hotstrip.linux.monitor.admin.service.impl;

import com.hotstrip.linux.monitor.admin.dto.ServerDTO;
import com.hotstrip.linux.monitor.admin.entity.ServerDO;
import com.hotstrip.linux.monitor.admin.mapper.ServerMapper;
import com.hotstrip.linux.monitor.admin.service.ServerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ServerServiceImpl implements ServerService {
    @Resource
    private ServerMapper serverMapper;

    @Override
    public void insertOrUpdate(ServerDTO serverDTO) {
        ServerDO serverDO = ServerDO.buildDO(serverDTO);
        if (serverDO.getId() == null) {
            serverMapper.insert(serverDO);
        } else {
            serverMapper.update(serverDO);
        }
    }

    @Override
    public void delete(List<Integer> ids) {
        int countRows = 0;
        for (Integer id : ids) {
            countRows += serverMapper.delete(id);
        }
        if (countRows < ids.size()) {
            log.error("delete server rows: {}/{}", countRows, ids.size());
        }
    }
}
