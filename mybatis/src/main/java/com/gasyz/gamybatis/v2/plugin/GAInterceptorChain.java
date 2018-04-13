package com.gasyz.gamybatis.v2.plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaoang on 2018/4/13.
 */
public class GAInterceptorChain {

    private final List<GAInterceptor> interceptors = new ArrayList<GAInterceptor>();

    public void addInterceptor(GAInterceptor gaInterceptor) {
        this.interceptors.add(gaInterceptor);
    }

    public Object PluginAll(Object target) {
        for (GAInterceptor gaInterceptor:interceptors) {
            target = gaInterceptor.plugin(target);
        }
        return target;
    }
}
