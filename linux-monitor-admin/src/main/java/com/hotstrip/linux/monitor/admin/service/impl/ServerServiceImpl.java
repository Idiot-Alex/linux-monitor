package com.hotstrip.linux.monitor.admin.service.impl;

import com.hotstrip.linux.monitor.admin.mapper.ServerMapper;
import com.hotstrip.linux.monitor.admin.page.PageParams;
import com.hotstrip.linux.monitor.admin.page.PageResult;
import com.hotstrip.linux.monitor.admin.pojo.dto.ServerDTO;
import com.hotstrip.linux.monitor.admin.pojo.entity.ServerDO;
import com.hotstrip.linux.monitor.admin.pojo.query.ServerQuery;
import com.hotstrip.linux.monitor.admin.pojo.vo.ServerVO;
import com.hotstrip.linux.monitor.admin.service.ServerService;
import com.hotstrip.linux.monitor.admin.transfer.ServerDataTransfer;
import com.hotstrip.linux.monitor.common.pojo.ServerData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ServerServiceImpl implements ServerService {
    @Resource
    private ServerMapper serverMapper;

    @Override
    public int insertOrUpdate(ServerDTO serverDTO) {
        ServerDO serverDO = ServerDO.buildDO(serverDTO);
        if (serverDO.getId() == null) {
            return serverMapper.insert(serverDO);
        } else {
            return serverMapper.update(serverDO);
        }
    }

    @Override
    public int delete(List<Integer> ids) {
        int countRows = 0;
        for (Integer id : ids) {
            countRows += serverMapper.delete(id);
        }
        return countRows;
    }

    @Override
    public ServerVO findById(int id) {
        return ServerVO.buildVO(serverMapper.findById(id));
    }

    @Override
    public PageResult<ServerVO> listByPage(ServerQuery serverQuery) {
        PageParams pageParams = serverQuery.getPageParams();
        int count = serverMapper.countByQuery(serverQuery);
        return PageResult.result(pageParams, count, () -> serverMapper.selectByQuery(serverQuery)
                .stream().map(item -> ServerVO.buildVO(item)).collect(Collectors.toList()));
    }

    @Override
    public List<ServerData> listAll() {
        return serverMapper.listAll().stream()
                .map(ServerDataTransfer::transferData)
                .collect(Collectors.toList());
    }
}
