package com.gasyz.pattern.singleton.lazy;

/**
 * Created by gaoang on 2018/3/16.
 */
public class LazyThree {

    private LazyThree() {}

    public static final LazyThree getInstance() {
        return LazyHolder.lazyThree;
    }

    //内部类在创建LazyThree的时候默认不加载
    private static class LazyHolder{
        private static final LazyThree lazyThree = new LazyThree();
    }

}
