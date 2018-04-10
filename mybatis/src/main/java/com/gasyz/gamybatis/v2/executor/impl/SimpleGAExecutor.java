package com.gasyz.gamybatis.v2.executor.impl;

import com.gasyz.gamybatis.v2.executor.GAExecutor;

/**
 * Created by gaoang on 2018/4/10.
 */
public class SimpleGAExecutor implements GAExecutor {

    public <T> T query(String statement, Object[] parameter) {
        System.out.println(statement);
        return null;
    }
}
