package com.hotstrip.linux.monitor.admin.service.impl;

import com.hotstrip.linux.monitor.admin.mapper.AdminUserMapper;
import com.hotstrip.linux.monitor.admin.page.PageParams;
import com.hotstrip.linux.monitor.admin.page.PageResult;
import com.hotstrip.linux.monitor.admin.pojo.dto.AdminUserDTO;
import com.hotstrip.linux.monitor.admin.pojo.entity.AdminUserDO;
import com.hotstrip.linux.monitor.admin.pojo.query.AdminUserQuery;
import com.hotstrip.linux.monitor.admin.pojo.vo.AdminUserVO;
import com.hotstrip.linux.monitor.admin.service.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public void insertOrUpdate(final AdminUserDTO adminUserDTO) {
        AdminUserDO adminUserDO = AdminUserDO.buildDO(adminUserDTO);
        if (adminUserDO.getId() == null) {
            adminUserMapper.insert(adminUserDO);
        } else {
            adminUserMapper.update(adminUserDO);
        }
    }

    @Override
    public void delete(final List<Integer> ids) {
        int countRows = 0;
        for (Integer id : ids) {
            countRows += adminUserMapper.delete(id);
        }
        if (countRows < ids.size()) {
            log.error("delete admin_user rows: {}/{}", countRows, ids.size());
        }
    }

    @Override
    public AdminUserVO findById(final Integer id) {
        return AdminUserVO.buildVO(adminUserMapper.findById(id));
    }

    @Override
    public PageResult<AdminUserVO> listByPage(final AdminUserQuery adminUserQuery) {
        PageParams pageParams = adminUserQuery.getPageParams();
        int count = adminUserMapper.countByQuery(adminUserQuery);
        return PageResult.result(pageParams, count, () -> adminUserMapper.selectByQuery(adminUserQuery)
                .stream().map(item -> AdminUserVO.buildVO(item)).collect(Collectors.toList()));
    }
}
