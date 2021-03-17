package com.hotstrip.linux.monitor.admin.controller;

import com.hotstrip.linux.monitor.admin.page.PageParams;
import com.hotstrip.linux.monitor.admin.page.PageResult;
import com.hotstrip.linux.monitor.admin.pojo.dto.ServerDTO;
import com.hotstrip.linux.monitor.admin.pojo.query.ServerQuery;
import com.hotstrip.linux.monitor.admin.pojo.vo.ServerVO;
import com.hotstrip.linux.monitor.admin.service.ServerService;
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
@RequestMapping("/server")
public class ServerController {

    @Resource
    private ServerService serverService;

    /**
     * get Server List
     * @param pageNo pageNo
     * @param pageSize pageSize
     * @param host host
     * @return {@linkplain Result}
     */
    @GetMapping("/list")
    public Result getServerList(final Integer pageNo,
                                final Integer pageSize,
                                final String host) {
        ServerQuery serverQuery = ServerQuery.builder()
                .pageParams(new PageParams(pageNo, pageSize))
                .host(host)
                .build();
        PageResult<ServerVO> pageResult = serverService.listByPage(serverQuery);
        return Result.success(Consts.SUCCESS_MESSAGE, pageResult);
    }

    /**
     * get Server by id
     * @param id primary key
     * @return {@linkplain Result}
     */
    @GetMapping("/{id}")
    public Result getServer(@PathVariable("id") final Integer id) {
        Objects.requireNonNull(id);
        ServerVO serverVO = serverService.findById(id);
        return Optional.ofNullable(serverVO).map(item -> {
            item.setPassword(Consts.STARTS);
            return Result.success(Consts.SUCCESS_MESSAGE, item);
        }).orElse(Result.error(Consts.ERROR, Consts.ERROR_MESSAGE));
    }

    /**
     * create Server
     * @param serverDTO {@linkplain ServerDTO}
     * @return {@linkplain Result}
     */
    @PostMapping("")
    public Result createServer(@RequestBody final ServerDTO serverDTO) {
        Objects.requireNonNull(serverDTO);
        // the password need to be encode
        int rows = serverService.insertOrUpdate(serverDTO);
        if (rows > 0) {
            return Result.success(Consts.SUCCESS_MESSAGE);
        }
        return Result.error(Consts.ERROR, Consts.ERROR_MESSAGE);
    }

    /**
     * update Server
     * @param id primary key
     * @param serverDTO {@linkplain ServerDTO}
     * @return {@linkplain Result}
     */
    @PutMapping("/{id}")
    public Result updateServer(@PathVariable("id") final Integer id,
                               @RequestBody final ServerDTO serverDTO) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(serverDTO);
        serverDTO.setId(id);
        // the password need to be encode
        int rows = serverService.insertOrUpdate(serverDTO);
        if (rows > 0) {
            return Result.success(Consts.SUCCESS_MESSAGE);
        }
        return Result.error(Consts.ERROR, Consts.ERROR_MESSAGE);
    }

    /**
     * delete batch server
     * @param ids primary key list
     * @return {@linkplain Result}
     */
    @Delete("/batch")
    public Result deleteServer(@RequestBody final List<Integer> ids) {
        Objects.requireNonNull(ids);
        int rows = serverService.delete(ids);
        if (rows < ids.size()) {
            log.error("delete server rows: {}/{}", rows, ids.size());
            return Result.error(Consts.ERROR, Consts.ERROR_MESSAGE);
        }
        return Result.success(Consts.SUCCESS_MESSAGE);
    }

}
