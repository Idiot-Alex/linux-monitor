package com.hotstrip.linux.monitor.admin.mapper;

import com.hotstrip.linux.monitor.admin.entity.AdminUserDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminUserMapper {

    /**
     * find AdminUser by id
     * @param id
     * @return {@linkplain AdminUserDO}
     */
    AdminUserDO findById(Integer id);

    /**
     * insert AdminUser
     * @param adminUserDO
     * @return rows
     */
    int insert(AdminUserDO adminUserDO);
}
