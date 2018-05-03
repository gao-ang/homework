package com.gasyz.spring.framework.context;

import com.gasyz.spring.demo.api.UserController;
import com.gasyz.spring.framework.annotation.Autowired;
import com.gasyz.spring.framework.annotation.Controller;
import com.gasyz.spring.framework.annotation.Service;
import com.gasyz.spring.framework.beans.BeanDefinition;
import com.gasyz.spring.framework.beans.BeanPostProcessor;
import com.gasyz.spring.framework.beans.BeanWrapper;
import com.gasyz.spring.framework.context.support.BeanDefinitionReader;
import com.gasyz.spring.framework.core.BeanFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext implements BeanFactory{

    private String [] configLocations;

    private BeanDefinitionReader reader;

    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();

    //用来保证注册式单例
    private Map<String,Object> beanCacheMap = new HashMap<String, Object>();

    //用来存储所有的被代理过的对象
    private Map<String,BeanWrapper> beanWrapperMap = new ConcurrentHashMap<String, BeanWrapper>();

    public ApplicationContext(String... configLocations) {
        this.configLocations = configLocations;
        this.refresh();
    }

    public void refresh() {
        //定位
        this.reader = new BeanDefinitionReader(configLocations);

        //加载
        List<String> beanDefinitions = reader.loadBeanDefinitions();

        //注册
        doRegisty(beanDefinitions);

        //依赖注入（lazy-init=false）,执行依赖注入
        doAutowired();

    }

    private void doAutowired() {
        for (Map.Entry<String,BeanDefinition> beanDefinationEntry:this.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinationEntry.getKey();
            if(!beanDefinationEntry.getValue().isLazyInit()) {
                getBean(beanName);
            }
        }

        for(Map.Entry<String,BeanWrapper> beanWrapperEntry : this.beanWrapperMap.entrySet()){
            populateBean(beanWrapperEntry.getKey(),beanWrapperEntry.getValue().getWrapperInstance());
        }

    }

    public void populateBean(String beanName,Object instance) {
        Class<?> clazz = instance.getClass();
        if (!(clazz.isAnnotationPresent(Controller.class)
                ||clazz.isAnnotationPresent(Service.class))) {
            return;
        }

        //读取所有的字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields) {
            if(!field.isAnnotationPresent(Autowired.class)){continue;}

            Autowired autowired = field.getAnnotation(Autowired.class);
            String autowiredBeanName = autowired.value().trim();
            if("".equals(autowiredBeanName)) {
                Class<?> type = field.getType();
                if(type.isInterface()) {
                    autowiredBeanName = field.getType().getName();
                } else {
                    autowiredBeanName = field.getName();
                }
            }
            field.setAccessible(true);
            try {
                BeanWrapper beanWrapper = this.beanWrapperMap.get(autowiredBeanName);
                field.set(instance,this.beanWrapperMap.get(autowiredBeanName).getWrapperInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void doRegisty(List<String> beanDefinitions) {
        try {
            for (String className:beanDefinitions) {
            //beanName:1默认首字母小写，2自定义名字3，接口注入
                Class<?> beanClass = Class.forName(className);
                if (beanClass.isInterface()) {
                    continue;
                }
                if (beanClass.isAnnotationPresent(Controller.class) || beanClass.isAnnotationPresent(Service.class)) {
                    BeanDefinition beanDefinition = reader.registerBean(className);
                    if (beanDefinition!=null) {
                        this.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
                    }

                    Class<?>[] interfaces = beanClass.getInterfaces();
                    for (Class<?> i:interfaces) {
                        //多个实现类，只能覆盖，可以自定义名字
                        this.beanDefinitionMap.put(i.getName(),beanDefinition);
                    }
                }


                //容器初始化完毕
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public  Object getBean(String beanName) {
        BeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
        String className = beanDefinition.getBeanClassName();
        try {
            //生成通知时间
            BeanPostProcessor beanPostProcessor = new BeanPostProcessor();

            Object instance = instantionBean(beanDefinition);
            if(null == instance) {return null;}

            //实例初始化之前调用一次
            beanPostProcessor.postProcessBeforeInitialization(instance,beanName);

            BeanWrapper beanWrapper = new BeanWrapper(instance);
            beanWrapper.setPostProcessor(beanPostProcessor);
            this.beanWrapperMap.put(beanName,beanWrapper);

            //实例初始化之后调用一次
            beanPostProcessor.postProcessAfterInitialization(instance,beanName);

            return  this.beanWrapperMap.get(beanName).getWrapperInstance();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return beanDefinition;
    }

    private synchronized Object instantionBean(BeanDefinition beanDefinition) {
        Object instance = null;
        String className = beanDefinition.getBeanClassName();
        try {
            if (this.beanCacheMap.containsKey(className)) {
                instance = this.beanCacheMap.get(className);
            } else {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
                this.beanCacheMap.put(className,instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }

    public Integer getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }

    public Properties getConfig() {
        return this.reader.getConfig();
    }

}
