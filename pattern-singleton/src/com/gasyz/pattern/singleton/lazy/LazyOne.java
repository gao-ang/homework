package com.gasyz.pattern.singleton.lazy;

/**
 * 懒汉式单例
 * 在外部需要时才初始化
 * Created by gaoang on 2018/3/16.
 */
public class LazyOne {
    private LazyOne(){}

    private static LazyOne lazyOne = null;

    public static LazyOne getInstance() {
        //存在线程安全的问题
        if (lazyOne ==  null) {
            lazyOne =  new LazyOne();
        }
        return lazyOne;
    }
}
