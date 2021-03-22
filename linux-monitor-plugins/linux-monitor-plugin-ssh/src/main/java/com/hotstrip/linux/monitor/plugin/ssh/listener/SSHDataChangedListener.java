package com.hotstrip.linux.monitor.plugin.ssh.listener;

import com.hotstrip.linux.monitor.common.listener.DataChangedEvent;
import com.hotstrip.linux.monitor.common.listener.DataChangedListener;
import com.hotstrip.linux.monitor.common.listener.EventSource;
import com.hotstrip.linux.monitor.common.pojo.ServerData;
import com.hotstrip.linux.monitor.common.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class SSHDataChangedListener implements DataChangedListener {

    @Override
    public void dataChanged(DataChangedEvent dataChangedEvent) {
        EventSource eventSource = dataChangedEvent.getEventSource();
        log.info(JacksonUtil.objectToJsonString(eventSource));
    }
}
