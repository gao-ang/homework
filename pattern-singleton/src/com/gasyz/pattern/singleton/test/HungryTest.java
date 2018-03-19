package com.gasyz.pattern.singleton.test;

import com.gasyz.pattern.singleton.hungry.Hungry;

/**
 * Created by gaoang on 2018/3/16.
 */
public class HungryTest {

    public static void main(String[] args) {
        Hungry h1 = Hungry.getInstance();
        Hungry h2 = Hungry.getInstance();
        System.out.println(h1 == h2);
    }
}
