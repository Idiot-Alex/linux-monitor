package com.hotstrip.linux.monitor.plugin.ssh;

import com.hotstrip.linux.monitor.common.LinuxMonitorThreadFactory;
import com.hotstrip.linux.monitor.common.listener.ServerStatus;
import com.hotstrip.linux.monitor.plugin.ssh.executor.ChannelExecutor;
import com.hotstrip.linux.monitor.plugin.ssh.executor.Executor;
import com.hotstrip.linux.monitor.plugin.ssh.executor.handler.LoadAvgHandler;
import com.hotstrip.linux.monitor.plugin.ssh.session.ConstPool;
import com.hotstrip.linux.monitor.plugin.ssh.session.SSHSessionManage;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
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
                    // 执行获取服务器状态的方法
                    log.info("schedule with fixed delay task...host: [{}]", session.getHost());
                    try {
                        Channel channel = session.openChannel(ConstPool.EXEC_CHANNEL);
                        // 获取 Mac OS 系统的平均负载命令 uptime | cut -d":" -f4- | sed s/,//g
                        ((ChannelExec) channel).setCommand("uptime | cut -d\":\" -f4- | sed s/,//g");

                        Executor executor = new ChannelExecutor((ChannelExec) channel);
                        executor.execute(new LoadAvgHandler());
                    } catch (JSchException e) {
                        e.printStackTrace();
                    }
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
