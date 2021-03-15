package com.hotstrip.linux.monitor.plugin.ssh.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class InputStreamUtil {

    /**
     * Stream to String
     * @param inputStream
     * @return
     */
    public static String streamToString(final InputStream inputStream) {
        byte[] tmp = new byte[1024];
        StringBuffer buffer = new StringBuffer();
        try {
            while(inputStream.available() > 0){
                int i = inputStream.read(tmp, 0, 1024);
                if (i < 0)
                    break;
                buffer.append(new String(tmp, 0, i));
            }
        } catch (IOException e) {
            log.error("read stream error, message: {}, caused: {}", e.getMessage(), e.getCause());
        }
        return buffer.toString();
    }
}
