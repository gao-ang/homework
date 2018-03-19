package com.gasyz.pattern.singleton.hungry;

/**
 * 饿汉式单例
        * 在类加载的时候就立即初始化，并创建单例对象
        * 优点：不加锁，执行效率高，用户体验比懒汉式好
        * 缺点：浪费了内存
        * 线程是安全的
        * Created by gaoang on 2018/3/16.
        */
public class Hungry {

    public static final Hungry hungry = new Hungry();

    //私有化构造方法，只能调用getInstance获取Hungry实例
    private Hungry(){}

    public static Hungry getInstance() {
        return hungry;
    }
}
