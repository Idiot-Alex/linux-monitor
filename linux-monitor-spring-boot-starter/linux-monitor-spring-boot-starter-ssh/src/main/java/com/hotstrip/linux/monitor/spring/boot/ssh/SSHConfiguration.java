package com.hotstrip.linux.monitor.spring.boot.ssh;

import com.hotstrip.linux.monitor.plugin.ssh.session.SSHSessionService;
import com.hotstrip.linux.monitor.plugin.ssh.session.DefaultSSHSessionServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SSHConfiguration {

    @Bean
    public SSHSessionService sshSessionService() {
        return new DefaultSSHSessionServiceImpl();
    }
}
