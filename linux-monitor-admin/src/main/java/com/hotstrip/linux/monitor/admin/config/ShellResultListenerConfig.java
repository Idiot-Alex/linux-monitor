package com.hotstrip.linux.monitor.admin.config;

import com.hotstrip.linux.monitor.admin.listener.SSHShellResultListener;
import com.hotstrip.linux.monitor.common.listener.ShellResultListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * config ShellResultListener Bean
 * @author Hotstrip
 */
@Configuration
public class ShellResultListenerConfig {

    /**
     * return a ShellResultListener Bean
     * if you want to add a Bean name, the value must be "shellResultListener"
     * or else, it still load the default Bean, which print log message to console only
     * @return
     */
    @Bean
    public ShellResultListener shellResultListener() {
        return new SSHShellResultListener();
    }
}
