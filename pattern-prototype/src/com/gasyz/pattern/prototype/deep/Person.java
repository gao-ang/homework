package com.gasyz.pattern.prototype.deep;

import java.io.*;
import java.util.Date;

/**
 * 深拷贝
 */
public class Person implements Cloneable,Serializable {

    public Telphone telphone;

    public Date time;

    public Person() {
        this.time = new Date();
    }

    public Object deepClone() throws Exception{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);

        Person copy = (Person)ois.readObject();
        return copy;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
