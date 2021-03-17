package com.hotstrip.linux.monitor.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final String DATE_FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT_DATETIME);

    /**
     * LocalDateTime to String with "yyyy-MM-dd HH:mm:ss" format
     * @param localDateTime {@linkplain LocalDateTime}
     * @return string
     */
    public static String localDateTimeToString(LocalDateTime localDateTime) {
        return DATE_TIME_FORMATTER.format(localDateTime);
    }
}
