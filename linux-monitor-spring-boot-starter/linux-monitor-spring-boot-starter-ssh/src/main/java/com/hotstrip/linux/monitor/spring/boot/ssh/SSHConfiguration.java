package com.hotstrip.linux.monitor.spring.boot.ssh;

import com.hotstrip.linux.monitor.plugin.ssh.SSHClientService;
import com.hotstrip.linux.monitor.plugin.ssh.session.DefaultSSHSessionServiceImpl;
import com.hotstrip.linux.monitor.plugin.ssh.session.SSHSessionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SSHConfiguration {

    @Bean
    public SSHSessionService sshSessionService() {
        return new DefaultSSHSessionServiceImpl();
    }

    @Bean
    public SSHClientService sshClientService() {
        return new SSHClientService();
    }
}
