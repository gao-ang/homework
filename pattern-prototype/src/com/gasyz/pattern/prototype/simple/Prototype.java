package com.gasyz.pattern.prototype.simple;

/**
 * Created by gaoang on 2018/3/16.
 */
public class Prototype implements Cloneable {
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
