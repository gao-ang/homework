package com.gasyz.pattern.prototype.simple;

import java.util.ArrayList;

/**
 * 浅拷贝，对象没有创建新的，只是把地址指了过去
 */
public class Prototype implements Cloneable {
    public String name;

    public BasketBall basketBall;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
