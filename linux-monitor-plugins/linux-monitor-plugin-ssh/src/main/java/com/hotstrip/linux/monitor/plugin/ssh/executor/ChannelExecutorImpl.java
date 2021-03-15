package com.hotstrip.linux.monitor.plugin.ssh.executor;

import com.hotstrip.linux.monitor.plugin.ssh.utils.InputStreamUtil;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class ChannelExecutorImpl implements Executor {

    private ChannelExec channelExec;

    public ChannelExecutorImpl(final ChannelExec channelExec) {
        this.channelExec = channelExec;
    }

    @Override
    public void execute(ExecutorHandler executorHandler) {
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
                    if(in.available() > 0) continue;
                    executeResult.setStatus(this.channelExec.getExitStatus());
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
        executorHandler.handle(executeResult);
    }
}
