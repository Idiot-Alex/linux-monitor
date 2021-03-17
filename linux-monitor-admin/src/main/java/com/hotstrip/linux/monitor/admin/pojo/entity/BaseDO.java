package com.hotstrip.linux.monitor.admin.pojo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@SuperBuilder
@NoArgsConstructor
public class BaseDO implements Serializable {

    private Integer id;
    private Timestamp createTime;
    private Timestamp updateTime;
}
