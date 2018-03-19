package com.gasyz.pattern.factory.func;

/**
 * Created by gaoang on 2018/3/14.
 */
public class FunctoryFactoryTest {
    public static void main(String[] args) {
        FunctoryFactory basketballFactory = new BasketballFactory();
        System.out.println(basketballFactory.getBall());
        FunctoryFactory footballFactory = new FootballFactory();
        System.out.println(footballFactory.getBall());
        FunctoryFactory badmintonFactory = new BadmintonFactory();
        System.out.println(badmintonFactory.getBall());
    }
}
