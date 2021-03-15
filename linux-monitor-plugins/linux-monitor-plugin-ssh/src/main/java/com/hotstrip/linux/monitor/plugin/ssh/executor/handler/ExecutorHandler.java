package com.hotstrip.linux.monitor.plugin.ssh.executor.handler;

import com.hotstrip.linux.monitor.plugin.ssh.executor.ExecuteResult;

public interface ExecutorHandler {

    void handle(final ExecuteResult executeResult);
}
