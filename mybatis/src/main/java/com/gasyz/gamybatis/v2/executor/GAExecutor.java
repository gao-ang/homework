package com.gasyz.gamybatis.v2.executor;

/**
 * Created by gaoang on 2018/4/9.
 */
public interface GAExecutor {
    <T> T query(String statement, Object[] parameter);
}
