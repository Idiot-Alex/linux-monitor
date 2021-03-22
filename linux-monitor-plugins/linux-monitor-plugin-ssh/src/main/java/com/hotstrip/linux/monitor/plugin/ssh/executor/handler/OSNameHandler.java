package com.hotstrip.linux.monitor.plugin.ssh.executor.handler;

import com.hotstrip.linux.monitor.common.Consts;
import com.hotstrip.linux.monitor.common.listener.ShellResultListener;
import com.hotstrip.linux.monitor.common.pojo.OSNameData;
import com.hotstrip.linux.monitor.plugin.ssh.executor.ExecuteResult;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

@Slf4j
public class OSNameHandler implements ExecutorHandler {
    @Override
    public void handle(ExecuteResult executeResult, ShellResultListener shellResultListener) {
        if (executeResult.getStatus() != Consts.EXIT_STATUS_0) {
            log.error("command exec exit code: [{}], result: [{}]", executeResult.getStatus(), executeResult.getResult());
            return;
        }
        InputStream is = new ByteArrayInputStream(executeResult.getResult().getBytes());
        Scanner scanner = new Scanner(is);
        final String osName = scanner.next();

        shellResultListener.osName(OSNameData.builder()
                .host(executeResult.getHost())
                .osName(osName)
                .build());
    }
}
