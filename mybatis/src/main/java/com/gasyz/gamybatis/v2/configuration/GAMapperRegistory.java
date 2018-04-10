package com.gasyz.gamybatis.v2.configuration;

import com.gasyz.gamybatis.v2.configuration.handler.ClassPathScanHandler;
import com.gasyz.gamybatis.v2.session.GASqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by gaoang on 2018/4/10.
 */
public class GAMapperRegistory {

    private final Map<Class<?>,GAMapperProxyFactory<?>> knownMappers = new HashMap();

    /**
     * 创建的时候，把该扫描的东西都放进去
     * @param scanPath
     */
    public GAMapperRegistory(String scanPath) {
        Set<Class<?>> mappers = new ClassPathScanHandler(scanPath).getMappers();//扫描包内的mapper
        this.addMappers(mappers);//注册mapper
    }

    public <T> T getMapper(Class<T> clazz, GASqlSession gaSqlSession) {
        GAMapperProxyFactory<T> gaMapperProxyFactory = (GAMapperProxyFactory)knownMappers.get(clazz);
        return gaMapperProxyFactory.newInstance(gaSqlSession);
    }

    private void addMappers(Set<Class<?>> mappers) {
        for (Class<?> type:mappers) {
            addMapper(type);
        }
    }

    private <T> void addMapper(Class<T> type) {
        this.knownMappers.put(type,new GAMapperProxyFactory(type));
    }
}
