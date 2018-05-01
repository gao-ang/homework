package com.gasyz.spring.framework.beans;

public class BeanWrapper {
    //会用到观察者模式
    //1.支持事件响应，会有一个监听
    private BeanPostProcessor postProcessor;

    private Object wrapperInstance;
    private Object originalInstance;

    public BeanWrapper(Object instance) {
        this.originalInstance = instance;
        this.wrapperInstance = instance;
    }

    public Object getWrapperInstance() {
        return wrapperInstance;
    }

    public void setWrapperInstance(Object wrapperInstance) {
        this.wrapperInstance = wrapperInstance;
    }

    public Object getOriginalInstance() {
        return originalInstance;
    }

    public void setOriginalInstance(Object originalInstance) {
        this.originalInstance = originalInstance;
    }

    //返回代理后的class
    public Class<?> getWrappedClass() {
        return  this.wrapperInstance.getClass();
    }

    public BeanPostProcessor getPostProcessor() {
        return postProcessor;
    }

    public void setPostProcessor(BeanPostProcessor postProcessor) {
        this.postProcessor = postProcessor;
    }
}
