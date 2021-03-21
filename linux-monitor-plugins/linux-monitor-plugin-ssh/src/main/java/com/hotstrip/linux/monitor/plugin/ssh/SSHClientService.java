package com.hotstrip.linux.monitor.plugin.ssh;

import com.hotstrip.linux.monitor.common.LinuxMonitorThreadFactory;
import com.hotstrip.linux.monitor.plugin.ssh.executor.Executor;
import com.hotstrip.linux.monitor.plugin.ssh.executor.handler.LoadAvgHandler;
import com.hotstrip.linux.monitor.plugin.ssh.session.SSHSessionManage;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Hotstrip
 * SSH client service
 */
@Slf4j
public class SSHClientService implements AutoCloseable {

    private final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    public SSHClientService() {
        this.scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10, LinuxMonitorThreadFactory.create("ssh-client", true));
        this.scheduledThreadPoolExecutor.scheduleWithFixedDelay(() -> {
            final List<Session> sessionList = SSHSessionManage.getInstance().getSessionList();
            for (Session session : sessionList) {
                if (session.isConnected()) {
                    log.info("schedule with fixed delay task...host: [{}]", session.getHost());

                }
            }
        }, 10, 30, TimeUnit.SECONDS);
    }

    @Override
    public void close() throws Exception {
        if (Objects.nonNull(scheduledThreadPoolExecutor)) {
            scheduledThreadPoolExecutor.shutdown();
        }
    }
}
