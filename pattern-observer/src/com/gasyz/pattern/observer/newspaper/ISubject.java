package com.gasyz.pattern.observer.newspaper;

import java.util.List;

/**
 * Created by gaoang on 2018/3/21.
 */
public interface ISubject {
   void sendMessage(List<IObserver> subList);
}
