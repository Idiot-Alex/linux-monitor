package com.hotstrip.linux.monitor.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
