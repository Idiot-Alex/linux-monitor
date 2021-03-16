package com.hotstrip.linux.monitor.plugin.ssh.executor;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExecuteResult {
    /**
     * return result after exec command
     */
    private String result;

    /**
     * return status after exec command
     * 0 means exit rightly
     */
    private int status;

    /**
     * return host after exec command
     */
    private String host;
}
