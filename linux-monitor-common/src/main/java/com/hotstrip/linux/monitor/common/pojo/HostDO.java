package com.hotstrip.linux.monitor.common.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HostDO {
    private String host;
    @Builder.Default
    private int port = 22;
    private String user;
    private String password;
    private String publicKey;
}
