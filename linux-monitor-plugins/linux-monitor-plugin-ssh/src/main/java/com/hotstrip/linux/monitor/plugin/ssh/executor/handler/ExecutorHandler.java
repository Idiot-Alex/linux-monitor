package com.hotstrip.linux.monitor.plugin.ssh.executor.handler;

import com.hotstrip.linux.monitor.common.listener.DataChangedListener;
import com.hotstrip.linux.monitor.common.listener.ShellResultListener;
import com.hotstrip.linux.monitor.plugin.ssh.executor.ExecuteResult;

public interface ExecutorHandler {

    void handle(ExecuteResult executeResult, ShellResultListener shellResultListener);
}
