package com.hotstrip.linux.monitor.admin.listener;

import com.hotstrip.linux.monitor.common.listener.ShellResultListener;
import com.hotstrip.linux.monitor.common.pojo.LoadAvgData;
import com.hotstrip.linux.monitor.common.pojo.OSNameData;
import com.hotstrip.linux.monitor.common.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("shellResultListener")
@Slf4j
public class SSHShellResultListener implements ShellResultListener {

    @Override
    public void osName(OSNameData osNameData) {
        log.info("admin...osNameData: [{}]", JacksonUtil.objectToJsonString(osNameData));
    }

    @Override
    public void loadAvg(LoadAvgData loadAvgData) {
        log.info("admin...loadAvgData: [{}]", JacksonUtil.objectToJsonString(loadAvgData));
    }
}
