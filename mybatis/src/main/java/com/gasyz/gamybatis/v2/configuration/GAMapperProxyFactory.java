package com.gasyz.gamybatis.v2.configuration;

import com.gasyz.gamybatis.v2.annotation.SelectSql;
import com.gasyz.gamybatis.v2.domain.User;
import com.gasyz.gamybatis.v2.mapper.UserMapper;
import com.gasyz.gamybatis.v2.session.GASqlSession;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaoang on 2018/4/10.
 */
public class GAMapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    private final Map<String,MapperData> mapperMethod = new HashMap<String, MapperData>();

    public GAMapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
        putMapperMethod(mapperInterface);//Mapper对应sql存储
    }

    public T newInstance(GAMapperProxy gaMapperProxy) {
        return (T)Proxy.newProxyInstance(this.mapperInterface.getClassLoader(),new Class[]{this.mapperInterface},gaMapperProxy);
    }

    public T newInstance(GASqlSession gaSqlSession) {
        GAMapperProxy gaMapperProxy = new GAMapperProxy(gaSqlSession, mapperInterface, mapperMethod);
        return this.newInstance(gaMapperProxy);
    }

    private void putMapperMethod(Class<T> mapperInterface) {
        Method[] methods = mapperInterface.getMethods();
        for (Method method:methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation: annotations) {
                if (annotation instanceof SelectSql) {
                    //mapperMethod.put(method.getName(),new MapperData(((SelectSql) annotation).value(),method.getReturnType()));
                    mapperMethod.put(method.getName(),new MapperData(((SelectSql) annotation).value(),mapperDataType(method)));
                }
            }
        }
    }

    private Class<?> mapperDataType(Method method) {
        Class<?> returnType = method.getReturnType();
        Object resultObj = new DefaultObjectFactory().create(returnType);
        if (resultObj  instanceof List) {
            Type genericReturnType = method.getGenericReturnType();
            Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
            returnType = (Class)actualTypeArguments[0];
        }
        return returnType;
    }

}
