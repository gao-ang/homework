package com.gasyz.pattern.factory.simple;

/**
 * Created by gaoang on 2018/3/14.
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {
        SimpleFactory simpleFactory = new SimpleFactory();
        System.out.println(simpleFactory.getBall("篮球"));
        System.out.println(simpleFactory.getBall("足球"));
        System.out.println(simpleFactory.getBall("羽毛球"));
        System.out.println(simpleFactory.getBall("其他"));
    }
}
