package com.gasyz.pattern.singleton.test;

import com.gasyz.pattern.singleton.enumtype.Ball;

/**
 * Created by gaoang on 2018/3/16.
 */
public class EnumTypeTest {
    public static void main(String[] args) {
        Ball b1 = Ball.BASEKTBALL;
        Ball b2 = Ball.BASEKTBALL;
        System.out.println(b1 == b2);
    }
}
