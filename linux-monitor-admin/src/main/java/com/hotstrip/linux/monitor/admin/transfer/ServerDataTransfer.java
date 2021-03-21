package com.hotstrip.linux.monitor.admin.transfer;

import com.hotstrip.linux.monitor.admin.pojo.entity.ServerDO;
import com.hotstrip.linux.monitor.common.pojo.ServerData;

import java.util.Optional;

public class ServerDataTransfer {

    /**
     * ServerDO to ServerData
     * @param serverDO {@linkplain ServerDO}
     * @return {@linkplain ServerData}
     */
    public static ServerData transferData(final ServerDO serverDO) {
        return Optional.of(serverDO).map(item -> ServerData.builder()
                .id(item.getId())
                .host(item.getHost())
                .user(item.getUser())
                .password(item.getPassword())
                .port(item.getPort())
                .build()
        ).orElse(null);
    }
}
