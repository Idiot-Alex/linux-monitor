package com.hotstrip.linux.monitor.plugin.ssh;

import com.hotstrip.linux.monitor.plugin.api.LinuxMonitorPlugin;
import com.hotstrip.linux.monitor.plugin.api.enums.PluginEnum;

/**
 * @author Hotstrip
 * SSH Plugin
 */
public class LinuxMonitorSSHPlugin implements LinuxMonitorPlugin {
    @Override
    public String named() {
        return PluginEnum.SSH.getName();
    }
}
