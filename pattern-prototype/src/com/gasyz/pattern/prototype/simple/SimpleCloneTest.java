package com.gasyz.pattern.prototype.simple;

import java.util.ArrayList;

/**
 * Created by gaoang on 2018/3/16.
 */
public class SimpleCloneTest {
    public static void main(String[] args) throws Exception {
        BasketBall ball = new BasketBall();
        ball.ballName="yy";
        ball.num = 2;
        ArrayList<Pojo> list = new ArrayList<>();
        list.add(new Pojo("aaa",333));
        ball.list=list;
        BasketBall clone = (BasketBall)ball.clone();
        System.out.println(clone.ballName+":"+clone.num+":"+clone.list.get(0));
        clone.show();
        System.out.println(ball == clone);
    }
}
