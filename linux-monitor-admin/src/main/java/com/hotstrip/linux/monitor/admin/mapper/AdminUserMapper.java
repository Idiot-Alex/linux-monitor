package com.hotstrip.linux.monitor.admin.mapper;

import com.hotstrip.linux.monitor.admin.entity.AdminUserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminUserMapper {

    /**
     * insert AdminUser
     * @param adminUserDO {@linkplain AdminUserDO}
     * @return rows
     */
    int insert(AdminUserDO adminUserDO);

    /**
     * update AdminUser
     * @param adminUserDO {@linkplain AdminUserDO}
     * @return rows
     */
    int update(AdminUserDO adminUserDO);

    /**
     * find AdminUser by id
     * @param id
     * @return {@linkplain AdminUserDO}
     */
    @Select("select * from admin_user where id = #{id}")
    AdminUserDO findById(@Param("id") Integer id);
}
