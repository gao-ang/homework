package com.gasyz.pattern.proxy.customer;

import com.gasyz.pattern.proxy.staticed.Person;

import java.lang.reflect.Method;

public class GPSupermarket implements GPInvocationHandler {
    private Person target;

    public Object getInstance(Person target) throws Exception{
        //2.把具体的人yy赋值给person
        this.target = target;
        Class<?> clazz = target.getClass();
        //3.生成$Proxy0，this具体指GPSupermarket，参数2获取到接口Person
        return GPProxy.newProxyInstance(new GPClassLoader(),clazz.getInterfaces(),this);
    }

    /**
     * 8.执行$Proxy0代理过来的方法，之前第2步已经注入了yy，这里执行的就是yy中的Method为recievePackage的方法
     *  完成整个动态过程
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是代理");
        method.invoke(this.target,args);
        System.out.println("完事");
        return  null;
    }
}
