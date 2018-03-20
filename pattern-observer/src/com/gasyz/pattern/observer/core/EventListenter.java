package com.gasyz.pattern.observer.core;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaoang on 2018/3/20.
 */
public class EventListenter {
    protected Map<Enum,Event> events = new HashMap<Enum,Event>();
    public void addLisenter(Enum eventType, Object target, Method callback) {
        events.put(eventType,new Event(target,callback));
    }

    private void trigger(Event e) throws Exception {
        e.setSource(this);
        e.getCallback().invoke(e.getTarget(),e);
    }


    protected void trigger(Enum call) throws Exception {
        if(!this.events.containsKey(call)) {
            return ;
        }
        trigger(this.events.get(call).setTrigger(call.toString()));
    }

}
