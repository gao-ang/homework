package com.gasyz.pattern.singleton.lazy;

/**
 * Double Check Locking 双检查锁机制
 * Created by gaoang on 2018/3/16.
 */
public class LazyFour {

    private LazyFour(){}

    private static LazyFour lazyFour = null;

    public static final LazyFour getInstance() {
        if (lazyFour == null) {
            synchronized (LazyFour.class) {
                if (lazyFour == null) {
                    lazyFour = new LazyFour();
                }
            }
        }
        return lazyFour;
    }
}
