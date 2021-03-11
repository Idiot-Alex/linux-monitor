package com.hotstrip.linux.monitor.client.core.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
//@EqualsAndHashCode(callSuper = true)
public final class ProcLoadAvgDO {

    private BigDecimal lavg1;
    private BigDecimal lavg5;
    private BigDecimal lavg15;

}
