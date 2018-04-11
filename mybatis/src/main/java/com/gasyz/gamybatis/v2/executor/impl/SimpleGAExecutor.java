package com.gasyz.gamybatis.v2.executor.impl;

import com.gasyz.gamybatis.v2.configuration.MapperData;
import com.gasyz.gamybatis.v2.executor.GAExecutor;
import com.gasyz.gamybatis.v2.executor.handler.GAStatementHandler;

import java.util.List;


/**
 * Created by gaoang on 2018/4/10.
 */
public class SimpleGAExecutor implements GAExecutor {


    public <E> List<E> query(MapperData mapperData, Object[] parameter) throws Exception {
        GAStatementHandler handler = new GAStatementHandler();
        return handler.query(mapperData,parameter);
    }
}
