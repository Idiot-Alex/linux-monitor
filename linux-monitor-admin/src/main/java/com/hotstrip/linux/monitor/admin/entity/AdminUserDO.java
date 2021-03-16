package com.hotstrip.linux.monitor.admin.entity;

import com.hotstrip.linux.monitor.admin.dto.AdminUserDTO;
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
public class AdminUserDO extends BaseDO {

    private String userName;
    private String password;

    /**
     * DTO to DO
     * @param adminUserDTO {@linkplain AdminUserDTO}
     * @return {@linkplain AdminUserDO}
     */
    public static AdminUserDO buildDO(final AdminUserDTO adminUserDTO) {
        return Optional.ofNullable(adminUserDTO).map(item -> {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            AdminUserDO adminUserDO = AdminUserDO.builder()
                    .userName(item.getUserName())
                    .password(item.getPassword())
                    .build();
            if (adminUserDTO.getId() == null) {
                adminUserDO.setCreateTime(currentTime);
            } else {
                adminUserDO.setId(item.getId());
                adminUserDO.setUpdateTime(currentTime);
            }
            return adminUserDO;
        }).orElse(null);
    }
}
