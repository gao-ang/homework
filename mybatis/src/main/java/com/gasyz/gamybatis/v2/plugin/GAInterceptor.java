package com.gasyz.gamybatis.v2.plugin;

import org.apache.ibatis.plugin.Invocation;

import java.util.Properties;

/**
 * Created by gaoang on 2018/4/13.
 */
public interface GAInterceptor {
    Object intercept(Invocation var1) throws Throwable;

    Object plugin(Object var1);

    void setProperties(Properties var1);
}
