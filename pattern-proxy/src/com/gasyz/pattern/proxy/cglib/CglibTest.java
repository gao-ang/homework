package com.gasyz.pattern.proxy.cglib;

import com.gasyz.pattern.proxy.staticed.Person;
import com.gasyz.pattern.proxy.staticed.Yy;

public class CglibTest {

    public static void main(String[] args) {
        Person person = (Person)new CglibSupermarket().getInstance(Yy.class);
        person.recievePackage();
        //System.out.println(person.getClass());
    }
}
