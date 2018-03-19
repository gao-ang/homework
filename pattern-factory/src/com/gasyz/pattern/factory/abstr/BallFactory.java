package com.gasyz.pattern.factory.abstr;

import com.gasyz.pattern.factory.domain.Badminton;
import com.gasyz.pattern.factory.domain.Ball;
import com.gasyz.pattern.factory.domain.BasketBall;
import com.gasyz.pattern.factory.domain.FootBall;

/**
 * Created by gaoang on 2018/3/14.
 */
public class BallFactory extends AbstractFactory {
    Ball getBasketball() {
        return new BasketBall();
    }

    Ball getFootball() {
        return new FootBall();
    }

    Ball getBadminton() {
        return new Badminton();
    }
}
