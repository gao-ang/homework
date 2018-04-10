package com.gasyz.gamybatis.v2.executor;

import com.gasyz.gamybatis.v2.configuration.MapperData;

/**
 * Created by gaoang on 2018/4/9.
 */
public interface GAExecutor {
    <T> T query(MapperData mapperData, Object[] parameter);
}
