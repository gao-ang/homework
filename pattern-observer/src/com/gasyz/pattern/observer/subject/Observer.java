package com.gasyz.pattern.observer.subject;

import com.gasyz.pattern.observer.core.Event;

/**
 * Created by gaoang on 2018/3/20.
 */
public class Observer {

    public void advice(Event event) {
        System.out.println("触发事件，打印日志");
    }
}
