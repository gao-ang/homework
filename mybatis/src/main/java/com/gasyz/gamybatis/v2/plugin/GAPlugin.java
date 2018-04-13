package com.gasyz.gamybatis.v2.plugin;

import com.gasyz.gamybatis.v2.exception.PluginException;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.ExceptionUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by gaoang on 2018/4/13.
 */
public class GAPlugin implements InvocationHandler {

    private final Object target;
    private final GAInterceptor gaInterceptor;
    private final Map<Class<?>, Set<Method>> signatureMaps;

    public GAPlugin(Object target, GAInterceptor gaInterceptor, Map<Class<?>, Set<Method>> signatureMaps) {
        this.target = target;
        this.gaInterceptor = gaInterceptor;
        this.signatureMaps = signatureMaps;
    }

    /**
     *
     * @param target 具体要执行的类，GAExecutor之类的
     * @param gaInterceptor 传入的Plugin
     * @return
     */
    public static Object Warp(Object target,GAInterceptor gaInterceptor) {
        Map<Class<?>, Set<Method>> signatureMap = getSignatureMap(gaInterceptor);//获取注解中要拦截的类和方法
        Class<?> type = target.getClass();
        Class<?>[] interfaces = getAllInterfaces(type, signatureMap);
        //若interfaces有走Plugin里面的方法，没有走原来的
        return interfaces.length > 0? Proxy.newProxyInstance(type.getClassLoader(), interfaces, new GAPlugin(target, gaInterceptor, signatureMap)):target;
    }

    /**
     * 获取定义的plugin中@Signature注解里面的类对应的方法集合
     * @param gaInterceptor
     * @return
     */
    private static Map<Class<?>, Set<Method>> getSignatureMap(GAInterceptor gaInterceptor) {
        Intercepts interceptsAnnotation = gaInterceptor.getClass().getAnnotation(Intercepts.class);
        if (interceptsAnnotation == null) {
            throw new PluginException("没有找到@Intercepts注解在"+gaInterceptor.getClass().getName());
        } else {
            Signature[] signatures = interceptsAnnotation.value();
            if (signatures !=null && signatures.length>1) {
                HashMap<Class<?>,Set<Method>> signatureMap = new HashMap();
                for (Signature signature:signatures) {
                    Set<Method> methods = signatureMap.get(signature.type());
                    if (methods == null) {
                        methods = new HashSet();
                        signatureMap.put(signature.type(),methods);
                    }
                    try {
                        Method method = signature.type().getMethod(signature.method(), signature.args());
                        methods.add(method);
                    } catch (NoSuchMethodException e) {
                        throw new PluginException("在"+signature.type()+"无法找到此方法"+signature.method(),e);
                    }
                }
                return signatureMap;
            } else {
                throw new PluginException("interceptsAnnotation中没有Signature");
            }
        }
    }

    /**
     *判断传入的type跟plugin@Signature注解里面的类是否对应，返回对应的类集合
     * @param type
     * @param signatureMap
     * @return
     */
    private static Class<?>[] getAllInterfaces(Class<?> type, Map<Class<?>, Set<Method>> signatureMap) {
        HashSet interfaces;
        //遍历type类的包含父类中的方法
        for (interfaces = new HashSet();type!=null;type = type.getSuperclass()) {
            Class<?>[] interfaces1 = type.getInterfaces();//获取类实现的接口集合
            for (Class<?> clazz:interfaces1) {
                if (signatureMap.containsKey(clazz)) {
                    interfaces.add(clazz);
                }
            }
        }
        return (Class[]) interfaces.toArray(new Class[interfaces.size()]);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            Set<Method> methods = (Set)this.signatureMaps.get(method.getDeclaringClass());
            if (methods != null && methods.contains(method)) {
               return this.gaInterceptor.intercept(new Invocation(this.target,method,args));
            }else {
               return method.invoke(this.target, args);
            }
        } catch (Exception var5) {
            throw ExceptionUtil.unwrapThrowable(var5);
        }
    }
}
