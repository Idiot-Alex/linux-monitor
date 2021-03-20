package com.hotstrip.linux.monitor.plugin.api;

/**
 * @author Hotstrip
 * Linux Monitor Plugin
 */
public interface LinuxMonitorPlugin {

    /**
     * plugin name
     * @return
     */
    String named();

    /**
     * plugin execute
     */
    void execute();
}
