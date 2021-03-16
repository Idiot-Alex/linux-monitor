package com.hotstrip.linux.monitor.admin.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringApplicationContextAware implements ApplicationContextAware {

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        SpringBeanUtil.getInstance().setApplicationContext(applicationContext);
    }
}
