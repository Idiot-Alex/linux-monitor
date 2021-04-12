package com.hotstrip.linux.monitor.common.listener;

import com.hotstrip.linux.monitor.common.pojo.CpuCoresData;
import com.hotstrip.linux.monitor.common.pojo.CpuUsageData;
import com.hotstrip.linux.monitor.common.pojo.LoadAvgData;
import com.hotstrip.linux.monitor.common.pojo.OSNameData;

public interface ShellResultListener {

    void osName(OSNameData osNameData);

    void loadAvg(LoadAvgData loadAvgData);

    void cpuCores(CpuCoresData cpuCoreData);

    void cpuUsage(CpuUsageData cpuUsageData);

}
