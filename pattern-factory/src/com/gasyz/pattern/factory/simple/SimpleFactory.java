package com.gasyz.pattern.factory.simple;

import com.gasyz.pattern.factory.domain.Badminton;
import com.gasyz.pattern.factory.domain.Ball;
import com.gasyz.pattern.factory.domain.BasketBall;
import com.gasyz.pattern.factory.domain.FootBall;

/**
 * Created by gaoang on 2018/3/14.
 */
public class SimpleFactory {
    public Ball getBall(String name) {
        if ("篮球".equals(name)) {
            return new BasketBall();
        }else if ("足球".equals(name)) {
            return new FootBall();
        }else if ("羽毛球".equals(name)) {
            return new Badminton();
        }else {
            System.out.println("生产不了该商品");
            return null;
        }
    }
}
