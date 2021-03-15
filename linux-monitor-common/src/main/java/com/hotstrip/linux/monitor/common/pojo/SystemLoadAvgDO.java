package com.hotstrip.linux.monitor.common.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
//@EqualsAndHashCode(callSuper = true)
public class SystemLoadAvgDO {

    private Double one;
    private Double five;
    private Double fifteen;

}
