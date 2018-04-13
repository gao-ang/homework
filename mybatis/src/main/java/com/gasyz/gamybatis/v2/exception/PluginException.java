package com.gasyz.gamybatis.v2.exception;

/**
 * Created by gaoang on 2018/4/13.
 */
public class PluginException extends RuntimeException {
    public PluginException() {
    }

    public PluginException(String message) {
        super(message);
    }

    public PluginException(String message, Throwable cause) {
        super(message, cause);
    }

    public PluginException(Throwable cause) {
        super(cause);
    }

}
