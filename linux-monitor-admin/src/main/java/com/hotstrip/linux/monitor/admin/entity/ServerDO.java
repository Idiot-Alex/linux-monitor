package com.hotstrip.linux.monitor.admin.entity;

import com.hotstrip.linux.monitor.admin.dto.AdminUserDTO;
import com.hotstrip.linux.monitor.admin.dto.ServerDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
import java.util.Optional;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ServerDO extends BaseDO {
    private String host;
    private String user;
    private String password;
    private Integer port;

    /**
     * DTO to DO
     * @param serverDTO {@linkplain ServerDTO}
     * @return {@linkplain ServerDO}
     */
    public static ServerDO buildDO(ServerDTO serverDTO) {
        return Optional.ofNullable(serverDTO).map(item -> {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            ServerDO serverDO = ServerDO.builder()
                    .host(item.getHost())
                    .user(item.getUser())
                    .password(item.getPassword())
                    .port(item.getPort())
                    .build();
            if (serverDTO.getId() == null) {
                serverDO.setCreateTime(currentTime);
            } else {
                serverDO.setId(serverDTO.getId());
                serverDO.setUpdateTime(currentTime);
            }
            return serverDO;
        }).orElse(null);
    }
}
