package com.hotstrip.linux.monitor.admin.pojo.vo;

import com.hotstrip.linux.monitor.admin.pojo.entity.ServerDO;
import com.hotstrip.linux.monitor.common.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServerVO implements Serializable {
    private Integer id;
    private String host;
    private String user;
    private String password;
    private Integer port;
    private String createTime;
    private String updateTime;

    /**
     * ServerDO to ServerVO
     * @param serverDO {@linkplain ServerDO}
     * @return {@linkplain ServerVO}
     */
    public static ServerVO buildVO(ServerDO serverDO) {
        return Optional.ofNullable(serverDO)
                .map(item ->
                    new ServerVO(item.getId(), item.getHost(),
                            item.getUser(), item.getPassword(), item.getPort(),
                            DateUtil.localDateTimeToString(item.getCreateTime().toLocalDateTime()),
                            DateUtil.localDateTimeToString(item.getUpdateTime().toLocalDateTime()))
        ).orElse(null);
    }
}
