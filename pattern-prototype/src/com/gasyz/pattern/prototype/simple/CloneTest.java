package com.gasyz.pattern.prototype.simple;

public class CloneTest {
    public static void main(String[] args) throws Exception {
        Prototype prototype = new Prototype();
        prototype.name="gaoang";
        BasketBall basketBall = new BasketBall();
        basketBall.name="篮球";
        basketBall.size = 10;
        prototype.basketBall =basketBall;
        Prototype clone = (Prototype)prototype.clone();

        System.out.println(prototype.basketBall);
        System.out.println(clone.basketBall);
    }
}
