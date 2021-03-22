package com.hotstrip.linux.monitor.plugin.ssh.listener;

import com.hotstrip.linux.monitor.common.listener.ShellResultListener;
import com.hotstrip.linux.monitor.common.pojo.LoadAvgData;
import com.hotstrip.linux.monitor.common.pojo.OSNameData;
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
}
