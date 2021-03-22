package com.hotstrip.linux.monitor.common.listener;

import com.hotstrip.linux.monitor.common.pojo.ServerData;

import java.util.List;

public interface DataChangedListener {

    default void dataChanged(DataChangedEvent dataChangedEvent) {

    }
}
