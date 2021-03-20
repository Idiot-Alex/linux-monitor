package com.hotstrip.linux.monitor.common;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public class LinuxMonitorThreadFactory implements ThreadFactory {

    private static final AtomicLong THREAD_NUMBER = new AtomicLong(1);
    private static final ThreadGroup THREAD_GROUP = new ThreadGroup("linux-monitor");

    private final boolean daemon;
    private final String namePrefix;
    private final int priority;

    public LinuxMonitorThreadFactory(final String namePrefix, final boolean daemon, final int priority) {
        this.daemon = daemon;
        this.namePrefix = namePrefix;
        this.priority = priority;
    }

    @Override
    public Thread newThread(final Runnable runnable) {
        Thread thread = new Thread(THREAD_GROUP, runnable,
                THREAD_GROUP.getName() + "-" + namePrefix + "-" + THREAD_NUMBER.getAndIncrement());
        thread.setDaemon(daemon);
        thread.setPriority(priority);
        return thread;
    }

    /**
     * create custom thread factory.
     * @param namePrefix prefix
     * @param daemon     daemon
     * @return {@linkplain ThreadFactory}
     */
    public static ThreadFactory create(final String namePrefix, final boolean daemon) {
        return create(namePrefix, daemon, Thread.NORM_PRIORITY);
    }

    /**
     * create custom thread factory.
     * @param namePrefix prefix
     * @param daemon     daemon
     * @param priority     priority
     * @return {@linkplain ThreadFactory}
     */
    public static ThreadFactory create(final String namePrefix, final boolean daemon, final int priority) {
        return new LinuxMonitorThreadFactory(namePrefix, daemon, priority);
    }
}
