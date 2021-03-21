package com.hotstrip.linux.monitor.plugin.ssh.executor.handler;

import com.hotstrip.linux.monitor.common.Consts;
import com.hotstrip.linux.monitor.common.pojo.SystemLoadAvgData;
import com.hotstrip.linux.monitor.plugin.ssh.executor.ExecuteResult;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

@Slf4j
public class LoadAvgHandler implements ExecutorHandler {

    @Override
    public void handle(final ExecuteResult executeResult) {
        if (executeResult.getStatus() != Consts.EXIT_STATUS_0) {
            log.error("command exec exit code: [{}]", executeResult.getStatus());
            return;
        }
        InputStream is = new ByteArrayInputStream(executeResult.getResult().getBytes());
        Scanner scanner = new Scanner(is).useDelimiter("\\s+");

        final double one = scanner.nextDouble();
        final double five = scanner.nextDouble();
        final double fifteen = scanner.nextDouble();

        final SystemLoadAvgData systemLoadAvgData = SystemLoadAvgData.builder()
                .host(executeResult.getHost())
                .one(one)
                .five(five)
                .fifteen(fifteen)
                .build();
        log.info("system loadavg: {}", systemLoadAvgData.toString());
    }
}
