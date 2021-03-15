package com.hotstrip.linux.monitor.plugin.ssh.executor.handler;

import com.hotstrip.linux.monitor.common.ConstResult;
import com.hotstrip.linux.monitor.common.pojo.SystemLoadAvgDO;
import com.hotstrip.linux.monitor.plugin.ssh.executor.ExecuteResult;
import com.hotstrip.linux.monitor.plugin.ssh.session.ConstPool;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

@Slf4j
public class LoadAvgHandler implements ExecutorHandler {

    @Override
    public void handle(final ExecuteResult executeResult) {
        if (executeResult.getStatus() != ConstResult.EXIT_STATUS_0) {
            log.error("command exec exit code: [{}]", executeResult.getStatus());
            return;
        }
        InputStream is = new ByteArrayInputStream(executeResult.getResult().getBytes());
        Scanner scanner = new Scanner(is).useDelimiter("\\s+");

        final double one = scanner.nextDouble();
        final double five = scanner.nextDouble();
        final double fifteen = scanner.nextDouble();

        final SystemLoadAvgDO systemLoadAvgDO = SystemLoadAvgDO.builder()
                .one(one)
                .five(five)
                .fifteen(fifteen)
                .build();

        log.info("system loadavg: {}", systemLoadAvgDO.toString());
    }
}
