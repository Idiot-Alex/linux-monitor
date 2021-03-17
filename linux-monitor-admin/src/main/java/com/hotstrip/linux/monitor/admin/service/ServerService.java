package com.hotstrip.linux.monitor.admin.service;

import com.hotstrip.linux.monitor.admin.dto.ServerDTO;

import java.util.List;

public interface ServerService {

    /**
     * insert or update Server
     * @param serverDTO {@linkplain ServerDTO}
     */
    void insertOrUpdate(ServerDTO serverDTO);

    /**
     * delete Server by Ids
     * @param ids primary keys into a list
     */
    void delete(List<Integer> ids);
}
