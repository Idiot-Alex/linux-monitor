package com.hotstrip.linux.monitor.plugin.ssh.executor.handler;

import com.hotstrip.linux.monitor.common.listener.ShellResultListener;
import com.hotstrip.linux.monitor.common.pojo.LoadAvgData;
import com.hotstrip.linux.monitor.plugin.ssh.executor.ExecuteResult;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

@Slf4j
public class LoadAvgHandler implements ExecutorHandler {

    @Override
    public void handle(final ExecuteResult executeResult, final ShellResultListener shellResultListener) {
        if (!executeResult.success()) {
            log.error("command exec exit code: [{}], result: [{}]", executeResult.getStatus(), executeResult.getResult());
            return;
        }
        InputStream is = new ByteArrayInputStream(executeResult.getResult().getBytes());
        Scanner scanner = new Scanner(is).useDelimiter("\\s+");

        final double one = scanner.nextDouble();
        final double five = scanner.nextDouble();
        final double fifteen = scanner.nextDouble();

        shellResultListener.loadAvg(LoadAvgData.builder()
                .host(executeResult.getHost())
                .one(one)
                .five(five)
                .fifteen(fifteen)
                .build());
    }
}
