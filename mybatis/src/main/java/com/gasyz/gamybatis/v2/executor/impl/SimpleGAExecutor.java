package com.gasyz.gamybatis.v2.executor.impl;

import com.gasyz.gamybatis.v2.configuration.MapperData;
import com.gasyz.gamybatis.v2.executor.GAExecutor;

/**
 * Created by gaoang on 2018/4/10.
 */
public class SimpleGAExecutor implements GAExecutor {

    public <T> T query(MapperData mapperData, Object[] parameter) {
        System.out.println(mapperData);
        return null;
    }
}
