package com.gasyz.pattern.strategy;

import com.gasyz.pattern.strategy.login.LoginType;

public class LoginStrategyTest {

    public static void main(String[] args) {
        User user = new User("1", "yy", "123");
        LoginState login = user.login(LoginType.QQ);
        System.out.println(login);
    }
}
