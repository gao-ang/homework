package com.gasyz.pattern.observer.subject;

import com.gasyz.pattern.observer.core.EventListenter;

/**
 * Created by gaoang on 2018/3/20.
 */
public class Subject extends EventListenter{

    //通常的话，采用动态代理实现监控，避免了代码的入侵
    public void add() throws Exception {
        System.out.println("调用添加的方法");
        trigger(SubjectEventType.ON_ADD);
    }

    public void delete() throws Exception {
        System.out.println("调用删除的方法");
        trigger(SubjectEventType.ON_RMOVE);
    }
}
