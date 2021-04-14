package com.hotstrip.linux.monitor.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemUsageData {

    private String host;
    private BigDecimal memTotal;
    private BigDecimal memUsed;
    private BigDecimal memFree;
    private BigDecimal memCache;
}
