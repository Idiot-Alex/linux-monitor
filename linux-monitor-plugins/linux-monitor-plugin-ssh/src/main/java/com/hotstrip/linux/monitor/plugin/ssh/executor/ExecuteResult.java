package com.hotstrip.linux.monitor.plugin.ssh.executor;

import com.hotstrip.linux.monitor.common.Consts;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

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
    private Integer status;

    /**
     * return host after exec command
     */
    private String host;

    /**
     * exec status
     * @return
     */
    public boolean success() {
        return this.getStatus() == Consts.EXIT_STATUS_0 && StringUtils.isNotBlank(this.getResult());
    }
}
