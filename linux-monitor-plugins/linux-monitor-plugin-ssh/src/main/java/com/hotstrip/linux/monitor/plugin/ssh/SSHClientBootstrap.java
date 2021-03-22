package com.hotstrip.linux.monitor.plugin.ssh;

import com.hotstrip.linux.monitor.common.LinuxMonitorThreadFactory;
import com.hotstrip.linux.monitor.common.listener.ShellResultListener;
import com.hotstrip.linux.monitor.plugin.ssh.client.SSHClient;
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
public class SSHClientBootstrap implements AutoCloseable {

    private final ShellResultListener shellResultListener;
    private final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    public SSHClientBootstrap(final ShellResultListener shellResultListener) {
        this.shellResultListener = shellResultListener;
        this.scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10, LinuxMonitorThreadFactory.create("ssh-client", true));
        this.scheduledThreadPoolExecutor.scheduleWithFixedDelay(() -> {
            final List<Session> sessionList = SSHSessionManage.getInstance().getSessionList();
            for (Session session : sessionList) {
                if (session.isConnected()) {
                    // 执行获取服务器状态的方法
                    log.info("schedule with fixed delay task...host: [{}]", session.getHost());
                    SSHClient sshClient = new SSHClient(session, this.shellResultListener);
                    sshClient.doExecute();
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
