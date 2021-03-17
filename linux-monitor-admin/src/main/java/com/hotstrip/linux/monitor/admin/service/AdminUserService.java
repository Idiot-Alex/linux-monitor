package com.hotstrip.linux.monitor.admin.service;

import com.hotstrip.linux.monitor.admin.page.PageResult;
import com.hotstrip.linux.monitor.admin.pojo.dto.AdminUserDTO;
import com.hotstrip.linux.monitor.admin.pojo.query.AdminUserQuery;
import com.hotstrip.linux.monitor.admin.pojo.vo.AdminUserVO;

import java.util.List;

public interface AdminUserService {

    /**
     * insert or update AdminUser
     * @param adminUserDTO {@linkplain AdminUserDTO}
     * @return rows
     */
    int insertOrUpdate(AdminUserDTO adminUserDTO);

    /**
     * delete AdminUser by Ids
     * @param ids primary keys into a list
     * @return rows
     */
    int delete(List<Integer> ids);

    /**
     * find AdminUser by id
     * @param id primary key
     * @return {@linkplain AdminUserVO}
     */
    AdminUserVO findById(Integer id);

    /**
     * list by page
     * @param adminUserQuery {@linkplain AdminUserQuery}
     * @return {@linkplain PageResult}
     */
    PageResult<AdminUserVO> listByPage(AdminUserQuery adminUserQuery);
}
