package com.hotstrip.linux.monitor.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServerPropertyData {
    private String host;
    private String osName;
    private Double one;
    private Double five;
    private Double fifteen;
    private Integer cores;
    private BigDecimal cpuUsage;
    private BigDecimal cpuUsr;
    private BigDecimal cpuSys;
    private BigDecimal cpuIoWait;
    private BigDecimal cpuSteal;
    private BigDecimal memTotal;
    private BigDecimal memUsed;
    private BigDecimal memFree;
    private BigDecimal memCache;

}
