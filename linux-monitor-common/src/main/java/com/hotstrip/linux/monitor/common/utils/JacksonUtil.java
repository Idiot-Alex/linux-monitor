package com.hotstrip.linux.monitor.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class JacksonUtil {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    /**
     * Object to String
     * @param obj {@linkplain Object}
     * @return
     * @throws Exception
     */
    @SneakyThrows
    public static String objectToJsonString(Object obj) {
        return objectMapper.writeValueAsString(obj);
    }
}
