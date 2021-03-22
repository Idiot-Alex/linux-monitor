package com.hotstrip.linux.monitor.common.listener;

import com.hotstrip.linux.monitor.common.pojo.LoadAvgData;
import com.hotstrip.linux.monitor.common.pojo.OSNameData;

public interface ShellResultListener {

    void osName(OSNameData osNameData);

    void loadAvg(LoadAvgData loadAvgData);
}
