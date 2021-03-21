package com.hotstrip.linux.monitor.admin.service;

import com.hotstrip.linux.monitor.admin.page.PageResult;
import com.hotstrip.linux.monitor.admin.pojo.dto.ServerDTO;
import com.hotstrip.linux.monitor.admin.pojo.query.ServerQuery;
import com.hotstrip.linux.monitor.admin.pojo.vo.ServerVO;
import com.hotstrip.linux.monitor.common.pojo.ServerData;

import java.util.List;

public interface ServerService {

    /**
     * insert or update Server
     * @param serverDTO {@linkplain ServerDTO}
     */
    int insertOrUpdate(ServerDTO serverDTO);

    /**
     * delete Server by Ids
     * @param ids primary keys into a list
     */
    int delete(List<Integer> ids);

    /**
     * find Server by id
     * @param id primary key
     * @return {@linkplain ServerVO}
     */
    ServerVO findById(int id);

    /**
     * list by page
     * @param serverQuery {@linkplain ServerQuery}
     * @return {@linkplain PageResult}
     */
    PageResult<ServerVO> listByPage(ServerQuery serverQuery);

    /**
     * list all ServerData
     * @return {@linkplain ServerData}
     */
    List<ServerData> listAll();
}
