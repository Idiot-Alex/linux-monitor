package com.hotstrip.linux.monitor.common.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
//@EqualsAndHashCode(callSuper = true)
public class SystemLoadAvgDO {

    private BigDecimal one;
    private BigDecimal five;
    private BigDecimal fifteen;

}
