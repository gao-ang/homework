package com.gasyz.pattern.observer.newspaper;

/**
 * Created by gaoang on 2018/3/21.
 */
public interface IManage {
    void registerSubscriber(IObserver observer);

    void removeSubscriber(IObserver observer);

    void sendPaper();
}
