package com.hotstrip.linux.monitor.admin.listener;

import com.hotstrip.linux.monitor.common.cache.ServerDataCache;
import com.hotstrip.linux.monitor.common.listener.ShellResultListener;
import com.hotstrip.linux.monitor.common.pojo.*;
import com.hotstrip.linux.monitor.common.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SSHShellResultListener implements ShellResultListener {

    @Override
    public void osName(OSNameData osNameData) {
        log.info("admin...osNameData: [{}]", JacksonUtil.objectToJsonString(osNameData));
        ServerPropertyData serverPropertyData = ServerPropertyData.builder()
                .host(osNameData.getHost())
                .osName(osNameData.getOsName())
                .build();
        ServerDataCache.getInstance().setCacheData(serverPropertyData);
    }

    @Override
    public void loadAvg(LoadAvgData loadAvgData) {
        log.info("admin...loadAvgData: [{}]", JacksonUtil.objectToJsonString(loadAvgData));
        ServerPropertyData serverPropertyData = ServerPropertyData.builder()
                .host(loadAvgData.getHost())
                .one(loadAvgData.getOne())
                .five(loadAvgData.getFive())
                .fifteen(loadAvgData.getFifteen())
                .build();
        ServerDataCache.getInstance().setCacheData(serverPropertyData);
    }

    @Override
    public void cpuCores(CpuCoresData cpuCoresData) {
        log.info("admin...cpuCoresData: [{}]", JacksonUtil.objectToJsonString(cpuCoresData));
        ServerPropertyData serverPropertyData = ServerPropertyData.builder()
                .host(cpuCoresData.getHost())
                .cores(cpuCoresData.getCores())
                .build();
        ServerDataCache.getInstance().setCacheData(serverPropertyData);
    }

    @Override
    public void cpuUsage(CpuUsageData cpuUsageData) {
        log.info("admin...cpuUsageData: [{}]", JacksonUtil.objectToJsonString(cpuUsageData));
        ServerPropertyData serverPropertyData = ServerPropertyData.builder()
                .host(cpuUsageData.getHost())
                .cpuUsage(cpuUsageData.getCpuUsage())
                .cpuUsr(cpuUsageData.getCpuUsr())
                .cpuSys(cpuUsageData.getCpuSys())
                .cpuIoWait(cpuUsageData.getCpuIoWait())
                .cpuSteal(cpuUsageData.getCpuSteal())
                .build();
        ServerDataCache.getInstance().setCacheData(serverPropertyData);
    }

    @Override
    public void memUsage(MemUsageData memUsageData) {
        log.info("admin...memUsageData: [{}]", JacksonUtil.objectToJsonString(memUsageData));
        ServerPropertyData serverPropertyData = ServerPropertyData.builder()
                .host(memUsageData.getHost())
                .memTotal(memUsageData.getMemTotal())
                .memUsed(memUsageData.getMemUsed())
                .memFree(memUsageData.getMemFree())
                .memCache(memUsageData.getMemCache())
                .build();
        ServerDataCache.getInstance().setCacheData(serverPropertyData);
    }
}
