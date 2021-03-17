package com.hotstrip.linux.monitor.admin.mapper;

import com.hotstrip.linux.monitor.admin.pojo.entity.AdminUserDO;
import com.hotstrip.linux.monitor.admin.pojo.query.AdminUserQuery;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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
     * delete AdminUser by id
     * @param id primary key
     * @return rows
     */
    @Delete("delete from admin_user where id = #{id}")
    int delete(@Param("id") Integer id);

    /**
     * find AdminUser by id
     * @param id
     * @return {@linkplain AdminUserDO}
     */
    @Select("select * from admin_user where id = #{id}")
    AdminUserDO findById(@Param("id") Integer id);

    /**
     * count by query
     * @param adminUserQuery {@linkplain AdminUserQuery}
     * @return row count
     */
    int countByQuery(AdminUserQuery adminUserQuery);

    /**
     * select by Page
     * @param adminUserQuery {@linkplain AdminUserQuery}
     * @return {@linkplain List}
     */
    List<AdminUserDO> selectByQuery(AdminUserQuery adminUserQuery);
}
