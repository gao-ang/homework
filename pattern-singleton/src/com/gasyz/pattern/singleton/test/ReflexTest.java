package com.gasyz.pattern.singleton.test;

import com.gasyz.pattern.singleton.lazy.LazyThree;

import java.lang.reflect.Constructor;

/**
 * 反射测试单例,反射会破环单例模式
 * Created by gaoang on 2018/3/16.
 */
public class ReflexTest {

    public static void main(String[] args) throws Exception {
        Class<LazyThree> clazz = LazyThree.class;
        Constructor<LazyThree> constructor = clazz.getDeclaredConstructor(null);
        constructor.setAccessible(true);

        LazyThree lazyThree = constructor.newInstance();
        LazyThree lazyThree1 = constructor.newInstance();
        LazyThree lazyThree2 = LazyThree.getInstance();
        System.out.println(lazyThree == lazyThree1);
        System.out.println(lazyThree == lazyThree2);
    }
}
