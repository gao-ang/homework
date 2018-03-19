package com.gasyz.pattern.strategy.login;

/**
 * Created by gaoang on 2018/3/19.
 */
public enum  LoginType {
    QQ(new QQLogin()),WECHAT(new WeChatLogin()),WEIBO(new WeiboLogin());

    private Login login;

    LoginType(Login login) {
        this.login = login;
    }

    public Login getLogin() {
        return login;
    }
}
