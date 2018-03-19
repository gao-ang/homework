package com.gasyz.pattern.factory.func;

import com.gasyz.pattern.factory.domain.Badminton;
import com.gasyz.pattern.factory.domain.Ball;

/**
 * Created by gaoang on 2018/3/14.
 */
public class BadmintonFactory implements FunctoryFactory {
    public Ball getBall() {
        return new Badminton();
    }
}
