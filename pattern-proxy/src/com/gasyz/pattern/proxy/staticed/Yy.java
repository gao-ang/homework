package com.gasyz.pattern.proxy.staticed;

import org.omg.CORBA.PUBLIC_MEMBER;

public class Yy implements Person {
    public void recievePackage() {
        System.out.println("我要收快递");
    }

    public void findJob() {
        System.out.println("我要找工作，年薪100万");
    }
}
