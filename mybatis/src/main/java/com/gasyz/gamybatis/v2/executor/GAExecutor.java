package com.gasyz.gamybatis.v2.executor;

import com.gasyz.gamybatis.v2.configuration.MapperData;

import java.util.List;

/**
 * Created by gaoang on 2018/4/9.
 */
public interface GAExecutor {
    <E> List<E> query(MapperData mapperData, Object[] parameter) throws Exception;
}
