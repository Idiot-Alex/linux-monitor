package com.hotstrip.linux.monitor.common.listener;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ServerStatus extends EventSource {

    private String host;
    private Double one;
    private Double five;
    private Double fifteen;
}
