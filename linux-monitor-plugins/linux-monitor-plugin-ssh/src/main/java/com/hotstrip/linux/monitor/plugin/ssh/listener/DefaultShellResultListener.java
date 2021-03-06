package com.hotstrip.linux.monitor.plugin.ssh.listener;

import com.hotstrip.linux.monitor.common.listener.ShellResultListener;
import com.hotstrip.linux.monitor.common.pojo.*;
import com.hotstrip.linux.monitor.common.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultShellResultListener implements ShellResultListener {
    @Override
    public void osName(OSNameData osNameData) {
        log.info("ssh...osNameData: [{}]", JacksonUtil.objectToJsonString(osNameData));
    }

    @Override
    public void loadAvg(LoadAvgData loadAvgData) {
        log.info("ssh...loadAvgData: [{}]", JacksonUtil.objectToJsonString(loadAvgData));
    }

    @Override
    public void cpuCores(CpuCoresData cpuCoresData) {
        log.info("ssh...cpuCoreData: [{}]", JacksonUtil.objectToJsonString(cpuCoresData));
    }

    @Override
    public void cpuUsage(CpuUsageData cpuUsageData) {
        log.info("ssh...cpuCoreData: [{}]", JacksonUtil.objectToJsonString(cpuUsageData));
    }

    @Override
    public void memUsage(MemUsageData memUsageData) {
        log.info("ssh...memUsageData: [{}]", JacksonUtil.objectToJsonString(memUsageData));
    }
}
