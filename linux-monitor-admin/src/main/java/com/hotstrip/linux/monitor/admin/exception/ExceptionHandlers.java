package com.hotstrip.linux.monitor.admin.exception;

import com.hotstrip.linux.monitor.ConstResult;
import com.hotstrip.linux.monitor.common.entity.Result;
import com.hotstrip.linux.monitor.common.exception.MonitorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 * ExceptionHandlers
 * @author Hotstrip
 * @Description handle exceptions
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandlers {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    protected Result serverExceptionHandler(final Exception exception) {
        log.error(exception.getMessage(), exception);
        String message;
        if (exception instanceof MonitorException) {
            MonitorException monitorException = (MonitorException) exception;
            message = monitorException.getMessage();
        } else {
            message = "The system is busy, please try again later";
        }
        return Result.error(ConstResult.ERROR, message);
    }

}
