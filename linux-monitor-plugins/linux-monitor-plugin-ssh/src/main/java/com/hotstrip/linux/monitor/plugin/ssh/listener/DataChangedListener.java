package com.hotstrip.linux.monitor.plugin.ssh.listener;

import com.hotstrip.linux.monitor.common.pojo.ServerData;

import java.util.List;

public interface DataChangedListener {

    default void onServerDataChanged(List<ServerData> serverDataList) {

    }
}
