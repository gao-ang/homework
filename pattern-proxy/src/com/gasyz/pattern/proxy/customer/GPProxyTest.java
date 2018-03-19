package com.gasyz.pattern.proxy.customer;

import com.gasyz.pattern.proxy.staticed.Person;
import com.gasyz.pattern.proxy.staticed.Yy;

/**
 * 按顺序点进去看整个过程，标明了顺序
 */
public class GPProxyTest {
    public static void main(String[] args)throws Exception {
        //1、传入具体的人(yy)，创建出$Proxy0
        Person obj = (Person)new GPSupermarket().getInstance(new Yy());
        System.out.println(obj.getClass());
        //5，获取到$Proxy0，这里是obj，执行recievePackage()方法，看下面的$Proxy0代码，上一步在GPProxy里面
        obj.recievePackage();
        obj.findJob();
    }
}

/*
生成的$Proxy0代码
package com.gasyz.pattern.proxy.customer;

        import com.gasyz.pattern.proxy.staticed.Person;
        import java.lang.reflect.Method;

public class $Proxy0 implements Person {
    GPInvocationHandler h;

    public $Proxy0(GPInvocationHandler var1) {
        this.h = var1;
    }

    //6、通过构造创建的时候，GPInvocationHandler h传入的是GPSupermarket
    public void recievePackage() {
        try {
            Method var1 = Person.class.getMethod("recievePackage");
            //7、执行执行GPSupermarket中的invoke方法，Method为recievePackage，参数为空
            this.h.invoke(this, var1, (Object[])null);
        } catch (Throwable var2) {
            var2.printStackTrace();
        }

    }

    public void findJob() {
        try {
            Method var1 = Person.class.getMethod("findJob");
            this.h.invoke(this, var1, (Object[])null);
        } catch (Throwable var2) {
            var2.printStackTrace();
        }

    }
}*/
