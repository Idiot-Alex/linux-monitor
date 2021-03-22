package com.hotstrip.linux.monitor.admin.listener;

import com.hotstrip.linux.monitor.common.listener.DataChangedEvent;
import com.hotstrip.linux.monitor.common.listener.DataChangedListener;
import com.hotstrip.linux.monitor.common.listener.EventSource;
import com.hotstrip.linux.monitor.common.utils.JacksonUtil;
import com.hotstrip.linux.monitor.plugin.ssh.listener.SSHDataChangedListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("dataChangedListener")
@Slf4j
public class ServerStatusDataListener implements DataChangedListener {

    @Override
    public void dataChanged(DataChangedEvent dataChangedEvent) {
        EventSource eventSource = dataChangedEvent.getEventSource();
        log.info("......{}", JacksonUtil.objectToJsonString(eventSource));
    }
}
