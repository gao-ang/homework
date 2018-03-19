package com.gasyz.pattern.factory.abstr;

import com.gasyz.pattern.factory.domain.Ball;

/**
 * Created by gaoang on 2018/3/14.
 */
public abstract class AbstractFactory {

    abstract Ball getBasketball();

    abstract Ball getFootball();

    abstract Ball getBadminton();
}
