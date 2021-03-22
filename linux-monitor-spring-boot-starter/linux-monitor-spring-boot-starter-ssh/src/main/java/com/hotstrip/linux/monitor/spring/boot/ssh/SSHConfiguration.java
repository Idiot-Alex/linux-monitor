package com.hotstrip.linux.monitor.spring.boot.ssh;

import com.hotstrip.linux.monitor.common.listener.DataChangedListener;
import com.hotstrip.linux.monitor.plugin.ssh.SSHClientBootstrap;
import com.hotstrip.linux.monitor.plugin.ssh.listener.SSHDataChangedListener;
import com.hotstrip.linux.monitor.plugin.ssh.session.DefaultSSHSessionServiceImpl;
import com.hotstrip.linux.monitor.plugin.ssh.session.SSHSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SSHConfiguration {

    @Bean
    public SSHSessionService sshSessionService() {
        return new DefaultSSHSessionServiceImpl();
    }

    @Bean("dataChangedListener")
    @ConditionalOnMissingBean(DataChangedListener.class)
    public DataChangedListener dataChangedListener() {
        return new SSHDataChangedListener();
    }

    @Bean
    public SSHClientBootstrap sshClientBootstrap(final DataChangedListener dataChangedListener) {
        return new SSHClientBootstrap(dataChangedListener);
    }


}
