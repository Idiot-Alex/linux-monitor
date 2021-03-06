package com.hotstrip.linux.monitor.plugin.ssh.executor;

import com.hotstrip.linux.monitor.common.listener.ShellResultListener;
import com.hotstrip.linux.monitor.common.utils.JacksonUtil;
import com.hotstrip.linux.monitor.plugin.ssh.executor.handler.ExecutorHandler;
import com.hotstrip.linux.monitor.plugin.ssh.utils.InputStreamUtil;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class ChannelExecutor implements Executor {

    private ChannelExec channelExec;

    private ShellResultListener shellResultListener;

    private ExecutorHandler executorHandler;

    public ChannelExecutor(final ChannelExec channelExec,
                           final ShellResultListener shellResultListener,
                           final ExecutorHandler executorHandler) {
        this.channelExec = channelExec;
        this.shellResultListener = shellResultListener;
        this.executorHandler = executorHandler;
    }

    @Override
    public void execute() {
        ExecuteResult executeResult = ExecuteResult.builder().build();
        try {
            InputStream in = this.channelExec.getInputStream();
            this.channelExec.connect(3000);
            while (true) {
                if (in.available() > 0) {
                    final String result = InputStreamUtil.streamToString(in);
                    executeResult.setResult(result);
                }
                if (this.channelExec.isClosed()) {
                    if (in.available() > 0) continue;
                    executeResult.setStatus(this.channelExec.getExitStatus());
                    executeResult.setHost(this.channelExec.getSession().getHost());
                    break;
                }
                Thread.sleep(200);
            }
        } catch (IOException | JSchException | InterruptedException e) {
            log.error("ChannelExecutor exec failed, message: {}, caused: {}", e.getMessage(), e.getCause());
        } finally {
            this.channelExec.disconnect();
        }
        this.executorHandler.handle(executeResult, this.shellResultListener);
    }
}
