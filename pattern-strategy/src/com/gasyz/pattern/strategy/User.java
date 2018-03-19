package com.gasyz.pattern.strategy;

import com.gasyz.pattern.strategy.login.LoginType;

/**
 * Created by gaoang on 2018/3/19.
 */
public class User {
    private String uid;
    private String username;
    private String password;

    public User(String uid, String username, String password) {
        this.uid = uid;
        this.username = username;
        this.password = password;
    }

    public LoginState login(LoginType loginType) {
        return loginType.getLogin().login(this.username,this.password);
    }
}
