package com.hotstrip.linux.monitor.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServerData {
    private Integer id;
    private String host;
    private String user;
    private String password;
    private Integer port;

}
