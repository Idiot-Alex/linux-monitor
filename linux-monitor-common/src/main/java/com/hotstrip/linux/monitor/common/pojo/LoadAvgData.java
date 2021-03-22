package com.hotstrip.linux.monitor.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoadAvgData {

    private String host;
    private Double one;
    private Double five;
    private Double fifteen;

}
