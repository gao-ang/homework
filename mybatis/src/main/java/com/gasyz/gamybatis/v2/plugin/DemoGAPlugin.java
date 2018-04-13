package com.gasyz.gamybatis.v2.plugin;

import com.gasyz.gamybatis.v2.executor.GAExecutor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.util.Properties;

/**
 * Created by gaoang on 2018/4/13.
 */
@Intercepts(@Signature(
        type = GAExecutor.class,
        method = "select",
        args = {}
))
public class DemoGAPlugin implements GAInterceptor {


    public Object intercept(Invocation var1) throws Throwable {
        return null;
    }

    /**
     * 将原来要执行的类加入plugin之后，返回
     * @param target
     * @return
     */
    public Object plugin(Object target) {
        return GAPlugin.Warp(target,this);
    }

    public void setProperties(Properties var1) {

    }
}
