package com.hotstrip.linux.monitor.common.listener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DataChangedEvent {
    private EventSource eventSource;

}
