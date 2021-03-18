package com.hotstrip.linux.monitor.admin.controller;

import com.hotstrip.linux.monitor.admin.page.PageParams;
import com.hotstrip.linux.monitor.admin.page.PageResult;
import com.hotstrip.linux.monitor.admin.pojo.dto.AdminUserDTO;
import com.hotstrip.linux.monitor.admin.pojo.query.AdminUserQuery;
import com.hotstrip.linux.monitor.admin.pojo.vo.AdminUserVO;
import com.hotstrip.linux.monitor.admin.service.AdminUserService;
import com.hotstrip.linux.monitor.common.Consts;
import com.hotstrip.linux.monitor.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/admin-user")
public class AdminUserController {

    @Resource
    private AdminUserService adminUserService;

    /**
     * login with userName and password
     * @param userName
     * @param password
     * @return {@linkplain Result}
     */
    public Result login(final String userName,
                        final String password) {
        Objects.requireNonNull(userName);
        Objects.requireNonNull(password);
        AdminUserVO adminUserVO = adminUserService.login(userName, password);
        if (Optional.ofNullable(adminUserVO).isPresent()) {
            return Result.success(Consts.SUCCESS_MESSAGE, adminUserVO);
        }
        return Result.error(Consts.ERROR, Consts.ERROR_MESSAGE);
    }

    /**
     * get AdminUserList api
     * @param pageNo pageNo
     * @param pageSize  pageSize
     * @param user userName
     * @return {@linkplain Result}
     */
    @GetMapping("/list")
    public Result getAdminUserList(final Integer pageNo,
                                   final Integer pageSize,
                                   final String user) {
        AdminUserQuery adminUserQuery = AdminUserQuery.builder()
                .pageParams(new PageParams(pageNo, pageSize))
                .user(user)
                .build();
        PageResult<AdminUserVO> pageResult = adminUserService.listByPage(adminUserQuery);
        return Result.success(Consts.SUCCESS_MESSAGE, pageResult);
    }

    /**
     * get AdminUser by id
     * @param id primary key
     * @return {@linkplain Result}
     */
    @GetMapping("/{id}")
    public Result getAdminUser(@PathVariable("id") final Integer id) {
        Objects.requireNonNull(id);
        AdminUserVO adminUserVO = adminUserService.findById(id);
        return Optional.ofNullable(adminUserVO).map(item -> {
            item.setPassword(Consts.STARTS);
            return Result.success(Consts.SUCCESS_MESSAGE, item);
        }).orElse(Result.error(Consts.ERROR, Consts.ERROR_MESSAGE));
    }

    /**
     * create AdminUser
     * @param adminUserDTO {@linkplain AdminUserDTO}
     * @return {@linkplain Result}
     */
    @PostMapping("")
    public Result createAdminUser(@RequestBody final AdminUserDTO adminUserDTO) {
        Objects.requireNonNull(adminUserDTO);
        // the password need to be encode
        int rows = adminUserService.insertOrUpdate(adminUserDTO);
        if (rows > 0) {
            return Result.success(Consts.SUCCESS_MESSAGE);
        }
        return Result.error(Consts.ERROR, Consts.ERROR_MESSAGE);
    }

    /**
     * update AdminUser
     * @param id primary key
     * @param adminUserDTO {@linkplain AdminUserDTO}
     * @return {@linkplain Result}
     */
    @PutMapping("/{id}")
    public Result updateAdminUser(@PathVariable("id") final Integer id,
                                  @RequestBody final AdminUserDTO adminUserDTO) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(adminUserDTO);
        adminUserDTO.setId(id);
        int rows = adminUserService.insertOrUpdate(adminUserDTO);
        if (rows > 0) {
            return Result.success(Consts.SUCCESS_MESSAGE);
        }
        return Result.error(Consts.ERROR, Consts.ERROR_MESSAGE);
    }

    /**
     * delete batch AdminUser
     * @param ids primary key list
     * @return {@linkplain Result}
     */
    @Delete("/batch")
    public Result deleteAdminUser(@RequestBody final List<Integer> ids) {
        Objects.requireNonNull(ids);
        int rows = adminUserService.delete(ids);
        if (rows < ids.size()) {
            log.error("delete admin_user rows: {}/{}", rows, ids.size());
            return Result.error(Consts.ERROR, Consts.ERROR_MESSAGE);
        }
        return Result.success(Consts.SUCCESS_MESSAGE);
    }
}
