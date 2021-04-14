package com.hotstrip.linux.monitor.common.listener;

import com.hotstrip.linux.monitor.common.pojo.*;

public interface ShellResultListener {

    void osName(OSNameData osNameData);

    void loadAvg(LoadAvgData loadAvgData);

    void cpuCores(CpuCoresData cpuCoreData);

    void cpuUsage(CpuUsageData cpuUsageData);

    void memUsage(MemUsageData memUsageData);

}
