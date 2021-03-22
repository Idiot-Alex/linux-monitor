package com.hotstrip.linux.monitor.plugin.ssh.executor.handler;

import com.hotstrip.linux.monitor.common.listener.ShellResultListener;
import com.hotstrip.linux.monitor.common.pojo.CpuCoreData;
import com.hotstrip.linux.monitor.plugin.ssh.executor.ExecuteResult;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

@Slf4j
public class CpuCoresHandler implements ExecutorHandler {
    @Override
    public void handle(ExecuteResult executeResult, ShellResultListener shellResultListener) {
        if (!executeResult.success()) {
            log.error("command exec exit code: [{}], result: [{}]", executeResult.getStatus(), executeResult.getResult());
            return;
        }
        InputStream is = new ByteArrayInputStream(executeResult.getResult().getBytes());
        Scanner scanner = new Scanner(is);
        final int cpuCores = scanner.nextInt();

        shellResultListener.cpuCores(CpuCoreData.builder()
                .host(executeResult.getHost())
                .cores(cpuCores)
                .build());
    }
}
