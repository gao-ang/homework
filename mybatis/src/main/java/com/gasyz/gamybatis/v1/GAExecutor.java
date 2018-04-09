package com.gasyz.gamybatis.v1;

/**
 * Created by gaoang on 2018/4/9.
 */
public interface GAExecutor {
    <T> T query(String statement, String parameter);
}
