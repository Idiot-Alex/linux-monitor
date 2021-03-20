package com.hotstrip.linux.monitor.plugin.ssh;

import com.hotstrip.linux.monitor.common.LinuxMonitorThreadFactory;

import java.util.Objects;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Hotstrip
 * SSH client service
 */
public class SSHClientService implements AutoCloseable {

    private final ScheduledThreadPoolExecutor executor;

    public SSHClientService() {
        this.executor = new ScheduledThreadPoolExecutor(10, LinuxMonitorThreadFactory.create("ssh-client", true));

        this.executor.scheduleWithFixedDelay(() -> {

        }, 10, 30, TimeUnit.SECONDS);
    }

    @Override
    public void close() throws Exception {
        if (Objects.nonNull(executor)) {
            executor.shutdown();
        }
    }
}
