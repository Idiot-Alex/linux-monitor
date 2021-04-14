package com.hotstrip.linux.monitor.plugin.ssh.executor.handler;

import com.hotstrip.linux.monitor.common.listener.ShellResultListener;
import com.hotstrip.linux.monitor.common.pojo.CpuUsageData;
import com.hotstrip.linux.monitor.common.pojo.MemUsageData;
import com.hotstrip.linux.monitor.plugin.ssh.executor.ExecuteResult;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Scanner;

@Slf4j
public class MemUsageHandler implements ExecutorHandler {
    @Override
    public void handle(ExecuteResult executeResult, ShellResultListener shellResultListener) {
        if (!executeResult.success()) {
            log.error("command exec exit code: [{}], result: [{}]", executeResult.getStatus(), executeResult.getResult());
            return;
        }
        InputStream is = new ByteArrayInputStream(executeResult.getResult().getBytes());
        Scanner scanner = new Scanner(is);
        final BigDecimal memTotal = scanner.nextBigDecimal().setScale(1, BigDecimal.ROUND_UP);
        final BigDecimal memUsed = scanner.nextBigDecimal().setScale(1, BigDecimal.ROUND_UP);
        final BigDecimal memFree = scanner.nextBigDecimal().setScale(1, BigDecimal.ROUND_UP);
        final BigDecimal memCache = scanner.nextBigDecimal().setScale(1, BigDecimal.ROUND_UP);

        shellResultListener.memUsage(MemUsageData.builder()
                .host(executeResult.getHost())
                .memTotal(memTotal)
                .memUsed(memUsed)
                .memFree(memFree)
                .memCache(memCache)
                .build());
    }
}
