package com.gasyz.pattern.factory.abstr;

/**
 * Created by gaoang on 2018/3/14.
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        BallFactory ballFactory = new BallFactory();
        System.out.println(ballFactory.getBasketball());
        System.out.println(ballFactory.getFootball());
        System.out.println(ballFactory.getBadminton());
    }
}
