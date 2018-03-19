package com.gasyz.pattern.singleton.test;

import com.gasyz.pattern.singleton.seriable.MySeriable;

import java.io.*;

public class SeriableTest {

    public static void main(String[] args) throws Exception {
        //Write Obj to file
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempFile"));
        oos.writeObject(MySeriable.getInstance());
        oos.flush();
        oos.close();
        //Read Obj from file
        File file = new File("tempFile");
        ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(file));
        MySeriable newInstance = (MySeriable) ois.readObject();
        ois.close();
        //判断是否是同一个对象
        System.out.println(newInstance == MySeriable.getInstance());
    }
}
