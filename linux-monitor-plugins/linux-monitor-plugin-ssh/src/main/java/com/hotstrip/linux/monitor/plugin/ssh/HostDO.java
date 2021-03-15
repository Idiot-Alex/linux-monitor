package com.hotstrip.linux.monitor.plugin.ssh;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class HostDO {
    private String host;
    @Builder.Default
    private int port = 22;
    private String user;
    private String password;
    private String publicKey;
}
