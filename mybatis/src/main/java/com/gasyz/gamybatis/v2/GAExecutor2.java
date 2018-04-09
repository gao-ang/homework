package com.gasyz.gamybatis.v2;

/**
 * Created by gaoang on 2018/4/9.
 */
public interface GAExecutor2 {
    <T> T query(String statement, String parameter);
}
