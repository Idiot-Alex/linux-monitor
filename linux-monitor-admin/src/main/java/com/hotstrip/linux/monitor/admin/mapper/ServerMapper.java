package com.hotstrip.linux.monitor.admin.mapper;

import com.hotstrip.linux.monitor.admin.entity.ServerDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ServerMapper {

    /**
     * insert Server
     * @param serverDO {@linkplain ServerDO}
     * @return rows
     */
    int insert(ServerDO serverDO);

    /**
     * update Server
     * @param serverDO {@linkplain ServerDO}
     * @return rows
     */
    int update(ServerDO serverDO);
}
