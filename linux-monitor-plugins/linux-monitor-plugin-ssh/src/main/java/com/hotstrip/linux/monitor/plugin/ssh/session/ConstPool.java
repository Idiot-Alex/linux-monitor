package com.hotstrip.linux.monitor.plugin.ssh.session;

public class ConstPool {

    public static final String COLON = ":";

    public static final String EXEC_CHANNEL = "exec";
    public static final String UNAME = "uname";
    public static final String LINUX_LOAD_AVG = "cat /proc/loadavg";
    public static final String LINUX_CPU_CORES = "cat /proc/cpuinfo |grep \"processor\" |wc -l";
    public static final String LINUX_CPU_USAGE = "mpstat 1 3|awk 'NR>6 {print $3, $5, $6, $9}'";
    public static final String LINUX_MEM_USAGE = "free -m | grep 'Mem' | awk '{print $2, $3, $4, $6}'";
}
