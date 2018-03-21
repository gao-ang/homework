package com.gasyz.pattern.observer.newspaper;

import com.sun.org.apache.bcel.internal.generic.ISUB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaoang on 2018/3/21.
 */
public class ManageImpl implements IManage {

    private List<IObserver> list = new ArrayList<>();
    private ISubject subject;

    public ManageImpl(ISubject subject) {
        this.subject = subject;
    }
    @Override
    public void registerSubscriber(IObserver observer) {
        list.add(observer);
    }

    @Override
    public void removeSubscriber(IObserver observer) {
        list.remove(observer);
    }

    @Override
    public void sendPaper() {
        subject.sendMessage(list);
    }
}
