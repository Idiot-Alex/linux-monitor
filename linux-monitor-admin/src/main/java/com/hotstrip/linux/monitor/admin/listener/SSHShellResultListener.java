package com.hotstrip.linux.monitor.admin.listener;

import com.hotstrip.linux.monitor.common.cache.ServerDataCache;
import com.hotstrip.linux.monitor.common.listener.ShellResultListener;
import com.hotstrip.linux.monitor.common.pojo.CpuCoreData;
import com.hotstrip.linux.monitor.common.pojo.LoadAvgData;
import com.hotstrip.linux.monitor.common.pojo.OSNameData;
import com.hotstrip.linux.monitor.common.pojo.ServerPropertyData;
import com.hotstrip.linux.monitor.common.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("shellResultListener")
@Slf4j
public class SSHShellResultListener implements ShellResultListener {

    @Override
    public void osName(OSNameData osNameData) {
        log.info("admin...osNameData: [{}]", JacksonUtil.objectToJsonString(osNameData));
        ServerPropertyData serverPropertyData = ServerPropertyData.builder()
                .host(osNameData.getHost())
                .osName(osNameData.getOsName())
                .build();
        ServerDataCache.getInstance().cacheData(serverPropertyData);
        log.info("admin...cache data: [{}]", ServerDataCache.getInstance().getData(osNameData.getHost()));
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
        ServerDataCache.getInstance().cacheData(serverPropertyData);
    }

    @Override
    public void cpuCores(CpuCoreData cpuCoreData) {
        log.info("admin...cpuCoreData: [{}]", JacksonUtil.objectToJsonString(cpuCoreData));
        ServerPropertyData serverPropertyData = ServerPropertyData.builder()
                .host(cpuCoreData.getHost())
                .cores(cpuCoreData.getCores())
                .build();
        ServerDataCache.getInstance().cacheData(serverPropertyData);
    }
}
