package com.hotstrip.linux.monitor.common.exception;

/**
 * MonitorException
 * @author Hotstrip
 */
public class MonitorException extends RuntimeException {
    private static final long serialVersionUID = 2755571265398393523L;
    
    public MonitorException(String message) {
        super(message);
    }

    public MonitorException(String message, Throwable cause) {
        super(message, cause);
    }

    public MonitorException(Throwable cause) {
        super(cause);
    }
}
