package com.gasyz.gamybatis.v2.configuration;


import com.gasyz.gamybatis.v2.session.GASqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by gaoang on 2018/4/10.
 */
public class GAMapperProxy<T> implements InvocationHandler {

    private GASqlSession gaSqlSession;

    private final Class<T> mapperInterface;

    private final Map<String, MapperData>  methodCache;

    public GAMapperProxy(GASqlSession gaSqlSession, Class<T> mapperInterface, Map<String, MapperData> methodCache) {
        this.gaSqlSession = gaSqlSession;
        this.mapperInterface = mapperInterface;
        this.methodCache = methodCache;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperData mapperData = methodCache.get(method.getName());
        if (mapperData != null) {
            return gaSqlSession.selectOne(mapperData,args);
        }
        return method.invoke(proxy,method);
    }
}
