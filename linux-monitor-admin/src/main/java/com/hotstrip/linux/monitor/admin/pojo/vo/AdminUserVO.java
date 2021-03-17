package com.hotstrip.linux.monitor.admin.pojo.vo;

import com.hotstrip.linux.monitor.admin.pojo.entity.AdminUserDO;
import com.hotstrip.linux.monitor.common.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserVO implements Serializable {
    private Integer id;
    private String userName;
    private String password;
    private String createTime;
    private String updateTime;

    /**
     * AdminUserDO to AdminUserVo
     * @param adminUserDO {@linkplain AdminUserDO}
     * @return {@linkplain AdminUserVO}
     */
    public static AdminUserVO buildVO(final AdminUserDO adminUserDO) {
        return Optional.ofNullable(adminUserDO)
                .map(item -> new AdminUserVO(item.getId(),
                        item.getUserName(), item.getPassword(),
                        DateUtil.localDateTimeToString(item.getCreateTime().toLocalDateTime()),
                        DateUtil.localDateTimeToString(item.getCreateTime().toLocalDateTime()))
        ).orElse(null);
    }
}
