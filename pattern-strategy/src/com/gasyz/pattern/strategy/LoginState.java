package com.gasyz.pattern.strategy;

/**
 * Created by gaoang on 2018/3/19.
 */
public class LoginState {
    private String code;
    private String msg;
    private Object data;

    public LoginState(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "LoginState{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
