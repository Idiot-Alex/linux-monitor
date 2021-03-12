package com.hotstrip.linux.monitor.admin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AdminUserDO extends BaseDO {

    private String name;
    private String password;

}
