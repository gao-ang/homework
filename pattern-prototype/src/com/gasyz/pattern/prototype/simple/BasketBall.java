package com.gasyz.pattern.prototype.simple;

import java.util.ArrayList;

/**
 * Created by gaoang on 2018/3/16.
 */
public class BasketBall extends Prototype {
    public String ballName;
    public Integer num;
    public ArrayList<Pojo> list;
    public void show() {
        System.out.println("篮球1");
    }
}
