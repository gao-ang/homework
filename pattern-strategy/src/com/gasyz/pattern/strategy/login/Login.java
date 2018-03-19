package com.gasyz.pattern.strategy.login;

import com.gasyz.pattern.strategy.LoginState;

/**
 * Created by gaoang on 2018/3/19.
 */
public interface Login {
    LoginState login(String username,String password);
}
