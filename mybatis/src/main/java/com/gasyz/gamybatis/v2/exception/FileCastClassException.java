package com.gasyz.gamybatis.v2.exception;

/**
 * Created by gaoang on 2018/4/10.
 */
public class FileCastClassException extends RuntimeException {
    public FileCastClassException() {
    }

    public FileCastClassException(String message) {
        super(message);
    }

    public FileCastClassException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileCastClassException(Throwable cause) {
        super(cause);
    }

}
