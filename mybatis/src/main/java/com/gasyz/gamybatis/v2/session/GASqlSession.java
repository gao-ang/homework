package com.gasyz.gamybatis.v2.session;

import com.gasyz.gamybatis.v2.configuration.MapperData;
import com.gasyz.gamybatis.v2.exception.TooManyResultsException;
import com.gasyz.gamybatis.v2.configuration.GAConfiguration;
import com.gasyz.gamybatis.v2.executor.GAExecutor;

import java.util.List;

/**
 * Created by gaoang on 2018/4/9.
 */
public class GASqlSession {

    private GAConfiguration gaConfiguration;

    private GAExecutor gaExecutor;

    public GASqlSession(GAConfiguration gaConfiguration, GAExecutor gaExecutor) {
        this.gaConfiguration = gaConfiguration;
        this.gaExecutor = gaExecutor;
    }

    public <T> T getMapper(Class<T> clazz) {
        return gaConfiguration.getMapper(clazz,this);
    }

    public <T> T selectOne(MapperData mapperData, Object[] parameter) {
        List<T> list = this.selectList(mapperData, parameter);
        if (list.size()==1) {
            return list.get(0);
        } else if (list.size()>1) {
            throw new TooManyResultsException("数据不止一个");
        } else {
            return null;
        }
    }

    public <E> List<E> selectList(MapperData mapperData, Object[] parameter) {
        return gaExecutor.query(mapperData,parameter);
    }
}
