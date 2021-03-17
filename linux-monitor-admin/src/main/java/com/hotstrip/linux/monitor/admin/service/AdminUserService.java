package com.hotstrip.linux.monitor.admin.service;

import com.hotstrip.linux.monitor.admin.dto.AdminUserDTO;
import com.hotstrip.linux.monitor.admin.vo.AdminUserVO;

import java.util.List;

public interface AdminUserService {

    /**
     * insert or update AdminUser
     * @param adminUserDTO {@linkplain AdminUserDTO}
     */
    void insertOrUpdate(AdminUserDTO adminUserDTO);

    /**
     * delete AdminUser by Ids
     * @param ids primary keys into a list
     */
    void delete(List<Integer> ids);

    /**
     * find AdminUser by id
     * @param id primary key
     * @return {@linkplain AdminUserVO}
     */
    AdminUserVO findById(Integer id);
}
