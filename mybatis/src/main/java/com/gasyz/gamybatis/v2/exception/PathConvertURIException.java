package com.gasyz.gamybatis.v2.exception;

/**
 * Created by gaoang on 2018/4/10.
 */
public class PathConvertURIException extends RuntimeException {

    public PathConvertURIException() {
    }

    public PathConvertURIException(String message) {
        super(message);
    }

    public PathConvertURIException(String message, Throwable cause) {
        super(message, cause);
    }

    public PathConvertURIException(Throwable cause) {
        super(cause);
    }
}
