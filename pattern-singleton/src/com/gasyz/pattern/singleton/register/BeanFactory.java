package com.gasyz.pattern.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册式单例模式，spring中的做法
 * Created by gaoang on 2018/3/16.
 */
public class BeanFactory {

    private BeanFactory() {}

    //由final修饰的map不能被重新new一次，但其实map中的值是可以被改变。
    //ConcurrentHashMap是线程安全的
    private static final Map<String,Object> ioc =new ConcurrentHashMap<String,Object>();

    //目前有线程安全的问题
    public static Object getBean(String className) {
        if (!ioc.containsKey("className")) {
            Object obj = null;
            try {
                 obj = Class.forName(className).newInstance();
                 ioc.put(className,obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        } else {
            return ioc.get(className);
        }
    }
}
