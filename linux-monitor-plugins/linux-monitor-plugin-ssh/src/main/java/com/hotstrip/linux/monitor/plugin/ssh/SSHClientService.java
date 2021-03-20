package com.hotstrip.linux.monitor.plugin.ssh;

import com.hotstrip.linux.monitor.common.LinuxMonitorThreadFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Hotstrip
 * SSH client service
 */
@Slf4j
public class SSHClientService implements AutoCloseable {

    private final ScheduledThreadPoolExecutor executor;

    public SSHClientService() {
        this.executor = new ScheduledThreadPoolExecutor(10, LinuxMonitorThreadFactory.create("ssh-client", true));

        this.executor.scheduleWithFixedDelay(() -> {
            log.info("schedule with fixed delay task...");
        }, 10, 30, TimeUnit.SECONDS);
    }

    @Override
    public void close() throws Exception {
        if (Objects.nonNull(executor)) {
            executor.shutdown();
        }
    }
}
