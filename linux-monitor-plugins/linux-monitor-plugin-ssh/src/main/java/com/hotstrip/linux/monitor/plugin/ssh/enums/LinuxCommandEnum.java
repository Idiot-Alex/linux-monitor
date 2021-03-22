package com.hotstrip.linux.monitor.plugin.ssh.enums;

import com.hotstrip.linux.monitor.plugin.ssh.session.ConstPool;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum LinuxCommandEnum {

    OS_NAME(ConstPool.UNAME),
    LOAD_AVG(ConstPool.LINUX_LOAD_AVG)
    ;

    private String command;
}
