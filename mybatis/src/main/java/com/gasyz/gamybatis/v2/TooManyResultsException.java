package com.gasyz.gamybatis.v2;

/**
 * Created by gaoang on 2018/4/9.
 */
public class TooManyResultsException extends RuntimeException {

    public TooManyResultsException(String message) {
        super(message);
    }
}
