package com.hotstrip.linux.monitor.admin.mapper;

import com.hotstrip.linux.monitor.admin.entity.ServerDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    /**
     * find by id
     * @param id
     * @return {@linkplain ServerDO}
     */
    @Select("select * from server where id = #{id}")
    ServerDO findById(@Param("id") Integer id);
}
