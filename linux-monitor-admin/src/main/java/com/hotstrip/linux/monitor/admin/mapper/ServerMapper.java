package com.hotstrip.linux.monitor.admin.mapper;

import com.hotstrip.linux.monitor.admin.entity.ServerDO;
import org.apache.ibatis.annotations.Delete;
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

    /**
     * delete Server by id
     * @param id primary key
     * @return rows
     */
    @Delete("delete from server where id = #{id}")
    int delete(@Param("id") Integer id);
}
