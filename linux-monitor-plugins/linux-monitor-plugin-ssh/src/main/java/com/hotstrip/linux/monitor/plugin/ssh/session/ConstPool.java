package com.hotstrip.linux.monitor.plugin.ssh.session;

public class ConstPool {

    public static final String COLON = ":";

    public static final String EXEC_CHANNEL = "exec";
    public static final String UNAME = "uname";
    public static final String LINUX_LOAD_AVG = "cat /proc/loadavg";
    public static final String LINUX_CPU_CORES = "cat /proc/cpuinfo |grep \"cores\"|uniq|awk '{print $4}'";
}
