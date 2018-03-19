package com.gasyz.pattern.proxy.staticed;

public class StaticProxyTest {
    public static void main(String[] args) {
        Supermarket supermarket = new Supermarket(new Yy());
        supermarket.proxyCourier();
    }
}
