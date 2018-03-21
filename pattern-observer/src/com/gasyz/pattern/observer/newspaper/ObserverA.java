package com.gasyz.pattern.observer.newspaper;

/**
 * Created by gaoang on 2018/3/21.
 */
public class ObserverA implements IObserver {
    @Override
    public void doing() {
        System.out.println("A收到报纸啦");
    }
}
