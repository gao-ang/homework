package com.gasyz.pattern.proxy.staticed;

/**
 * 超市代收快递
 */
public class Supermarket {

    private Person person;

    public Supermarket(Person person){
        this.person = person;
    }

    public void proxyCourier() {
        person.recievePackage();
        System.out.println("超市代收快递");
    }
}
