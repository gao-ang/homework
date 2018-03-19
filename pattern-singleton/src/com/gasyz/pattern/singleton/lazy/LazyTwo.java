package com.gasyz.pattern.singleton.lazy;

/**
 * Created by gaoang on 2018/3/16.
 */
public class LazyTwo {
    private LazyTwo() {}

    public static LazyTwo lazyTwo = null;

    //加入同步锁，可以解决线程安全问题，但会带来性能问题
    public static synchronized LazyTwo getInstance() {
        if (lazyTwo == null) {
            lazyTwo = new LazyTwo();
        }
        return lazyTwo;
    }
}
