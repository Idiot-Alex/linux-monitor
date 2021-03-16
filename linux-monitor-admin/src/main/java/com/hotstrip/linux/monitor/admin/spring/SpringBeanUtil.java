package com.hotstrip.linux.monitor.admin.spring;

import org.springframework.context.ApplicationContext;

public class SpringBeanUtil {
    private ApplicationContext applicationContext;

    private SpringBeanUtil() {
    }

    public static class SpringBeanUtilHolder {
        public static final SpringBeanUtil INSTANCE = new SpringBeanUtil();
    }

    public static final SpringBeanUtil getInstance() {
        return SpringBeanUtilHolder.INSTANCE;
    }

    /**
     * set ApplicationContext
     * @param applicationContext
     */
    public void setApplicationContext(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * get Spring bean
     * @param clazz
     * @param <T>
     * @return bean
     */
    public <T> T getBean(final Class<T> clazz) {
        return this.applicationContext.getBean(clazz);
    }
}
