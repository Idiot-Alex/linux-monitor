package com.hotstrip.linux.monitor.admin.listener;

import com.hotstrip.linux.monitor.admin.service.ServerService;
import com.hotstrip.linux.monitor.common.pojo.ServerData;
import com.hotstrip.linux.monitor.plugin.ssh.session.SSHSessionService;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SSHClientListener implements ApplicationListener<ApplicationStartedEvent> {

    @Resource
    private ServerService serverService;
    @Resource
    private SSHSessionService sshSessionService;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        final List<ServerData> list = serverService.listAll();
        for (ServerData serverData : list) {
            sshSessionService.openSession(serverData);
        }
    }
}
