package com.hotstrip.linux.monitor.plugin.ssh.executor;

import com.hotstrip.linux.monitor.common.listener.DataChangedListener;
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

    private DataChangedListener dataChangedListener;

    private ExecutorHandler executorHandler;

    public ChannelExecutor(final ChannelExec channelExec, final DataChangedListener dataChangedListener, final ExecutorHandler executorHandler) {
        this.channelExec = channelExec;
        this.dataChangedListener = dataChangedListener;
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
            e.printStackTrace();
            log.error("ChannelExecutor exec failed, message: {}, caused: {}", e.getMessage(), e.getCause());
        } finally {
            this.channelExec.disconnect();
        }
        this.executorHandler.handle(executeResult, this.dataChangedListener);
    }
}
