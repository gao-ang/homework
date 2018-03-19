package com.gasyz.pattern.strategy.login;

import com.gasyz.pattern.strategy.LoginState;

/**
 * Created by gaoang on 2018/3/19.
 */
public class WeiboLogin implements Login{
    @Override
    public LoginState login(String username, String password) {
        System.out.println("使用微博登陆");
        System.out.println("登陆中...");
        return new LoginState("SUCCESS","微博登陆成功",username);
    }
}
