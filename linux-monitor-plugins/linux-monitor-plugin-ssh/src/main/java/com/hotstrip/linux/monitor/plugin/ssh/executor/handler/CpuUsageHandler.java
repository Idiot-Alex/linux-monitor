package com.hotstrip.linux.monitor.plugin.ssh.executor.handler;

import com.hotstrip.linux.monitor.common.listener.ShellResultListener;
import com.hotstrip.linux.monitor.common.pojo.CpuCoresData;
import com.hotstrip.linux.monitor.common.pojo.CpuUsageData;
import com.hotstrip.linux.monitor.plugin.ssh.executor.ExecuteResult;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Scanner;

@Slf4j
public class CpuUsageHandler implements ExecutorHandler {
    @Override
    public void handle(ExecuteResult executeResult, ShellResultListener shellResultListener) {
        if (!executeResult.success()) {
            log.error("command exec exit code: [{}], result: [{}]", executeResult.getStatus(), executeResult.getResult());
            return;
        }
        InputStream is = new ByteArrayInputStream(executeResult.getResult().getBytes());
        Scanner scanner = new Scanner(is);
        final BigDecimal cpuUsage = scanner.nextBigDecimal().setScale(1, BigDecimal.ROUND_UP);

        shellResultListener.cpuUsage(CpuUsageData.builder()
                .host(executeResult.getHost())
                .cpuUsage(cpuUsage)
                .build());
    }
}
