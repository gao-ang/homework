package com.gasyz.pattern.factory.func;

import com.gasyz.pattern.factory.domain.Ball;
import com.gasyz.pattern.factory.domain.FootBall;

/**
 * Created by gaoang on 2018/3/14.
 */
public class FootballFactory implements FunctoryFactory {
    public Ball getBall() {
        return new FootBall();
    }
}
