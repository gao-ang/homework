package com.gasyz.pattern.observer.newspaper;

import java.util.List;

/**
 * Created by gaoang on 2018/3/21.
 */
public class SubjectImpl implements ISubject {
    @Override
    public void sendMessage(List<IObserver> subList) {
        System.out.println("邮局发送报纸");
        for (IObserver observer:subList) {
            observer.doing();
        }
    }
}
