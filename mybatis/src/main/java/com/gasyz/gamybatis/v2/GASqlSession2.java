package com.gasyz.gamybatis.v2;

import java.util.List;

/**
 * Created by gaoang on 2018/4/9.
 */
public class GASqlSession2 {

    private GAConfiguration2 gaConfiguration2;

    private GAExecutor2 gaExecutor2;

    public GASqlSession2(GAConfiguration2 gaConfiguration2, GAExecutor2 gaExecutor2) {
        this.gaConfiguration2 = gaConfiguration2;
        this.gaExecutor2 = gaExecutor2;
    }

    public <T> T getMapper(Class<T> clazz) {
        return gaConfiguration2.getMapper(clazz);
    }

    public <T> T selectOne(String statement,String parameter) {
        List<T> list = this.selectList(statement, parameter);
        if (list.size()==1) {
            return list.get(0);
        } else if (list.size()>1) {
            throw new TooManyResultsException("数据不止一个");
        } else {
            return null;
        }
    }
    public <E> List<E> selectList(String statement, String parameter) {
        return gaExecutor2.query(statement,parameter);
    }
}
