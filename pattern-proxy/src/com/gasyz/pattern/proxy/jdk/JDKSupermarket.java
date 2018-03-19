package com.gasyz.pattern.proxy.jdk;

import com.gasyz.pattern.proxy.staticed.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKSupermarket implements InvocationHandler {

    private Person person;

    public Object getInstance(Person person) throws Exception {
        this.person = person;
        Class<? extends Person> clazz = person.getClass();

        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是超市");
        System.out.println("帮忙代收快递");
        method.invoke(this.person,args);
        System.out.println("完事");
        return null;
    }
}
