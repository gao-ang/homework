package com.gasyz.pattern.singleton.seriable;

import java.io.Serializable;

/**
 * Created by gaoang on 2018/3/16.
 */
public class MySeriable implements Serializable {
    //序列化就是说把内存中的状态通过转换成字节码的形式
    //从而转换一个IO流，写入到其他地方(可以是磁盘、网络IO)
    //内存中状态给永久保存下来了

    //反序列化
    //讲已经持久化的字节码内容，转换为IO流
    //通过IO流的读取，进而将读取的内容转换为Java对象
    //在转换过程中会重新创建对象new

    public final static MySeriable mySeriable = new MySeriable();

    private MySeriable() {}

    public static MySeriable getInstance() {
        return mySeriable;
    }

    /**
     * 解决序列化中的单例问题
     * 该方法在反序列化时会被调用，该方法不是接口定义的方法，有点儿约定俗成的感觉
     * @return
     */
    private  Object readResolve(){
        return  mySeriable;
    }

}
