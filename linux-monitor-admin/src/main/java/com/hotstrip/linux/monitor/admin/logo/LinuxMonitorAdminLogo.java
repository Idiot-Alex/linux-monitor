package com.hotstrip.linux.monitor.admin.logo;

import com.hotstrip.linux.monitor.common.logo.LinuxMonitorLogo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.logging.LoggingApplicationListener;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;

@Slf4j
@Order(LoggingApplicationListener.DEFAULT_ORDER + 1)
public class LinuxMonitorAdminLogo implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent applicationEnvironmentPreparedEvent) {
        log.info(LinuxMonitorLogo.buildBannerText());
    }
}
