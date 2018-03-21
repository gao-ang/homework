package com.gasyz.pattern.observer.newspaper;

/**
 * Created by gaoang on 2018/3/21.
 */
public class ObserverTest {
    public static void main(String[] args) {
        ObserverA observerA = new ObserverA();
        ObserverA observerA1 = new ObserverA();

        SubjectImpl subject = new SubjectImpl();
        ManageImpl manage = new ManageImpl(subject);
        manage.registerSubscriber(observerA);
        manage.registerSubscriber(observerA1);
        manage.sendPaper();

        manage.removeSubscriber(observerA1);
        manage.sendPaper();
    }
}
