package com.hotstrip.linux.monitor.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OSNameData {

    private String host;
    private String osName;
}
