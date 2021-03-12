package com.hotstrip.linux.monitor.common.entity;

import com.hotstrip.linux.monitor.ConstResult;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Result for api
 * @author hotstrip
 */
@Data
@NoArgsConstructor
public class Result {

    private int code;
    private String message;
    private Object data;

    /**
     * full params constructor
     * @param code
     * @param message
     * @param data
     */
    public Result(final int code, final String message, final Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * return success result with message
     * @param message message.
     * @return {@linkplain Result}
     */
    public static Result success(final String message) {
        return success(message, null);
    }

    /**
     * return success result with data
     * @param data data.
     * @return {@linkplain Result}
     */
    public static Result success(final Object data) {
        return success(ConstResult.SUCCESS_MESSAGE, null);
    }

    /**
     * return success result with message and data
     * @param message message.
     * @param data data.
     * @return {@linkplain Result}
     */
    public static Result success(final String message, final Object data) {
        return new Result(ConstResult.SUCCESS, message, data);
    }

    /**
     * return error result with code and message
     * @param code code.
     * @param message message
     * @return {@linkplain Result}
     */
    public static Result error(final int code, final String message) {
        return error(code, message, null);
    }

    /**
     * return error result with code and data
     * @param code code.
     * @param data data.
     * @return {@linkplain Result}
     */
    public static Result error(final int code, final Object data) {
        return error(code, ConstResult.ERROR_MESSAGE, data);
    }

    /**
     * return error result width full params
     * @param code code.
     * @param message message.
     * @param data data.
     * @return {@linkplain Result}
     */
    public static Result error(final int code, final String message, final Object data) {
        return new Result(code, message, data);
    }

}
