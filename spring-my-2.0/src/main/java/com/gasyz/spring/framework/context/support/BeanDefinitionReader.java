package com.gasyz.spring.framework.context.support;

import com.gasyz.spring.framework.beans.BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//用对配置文件进行查找，读取，解析
public class BeanDefinitionReader {

    private Properties config= new Properties();

    private List<String> registyBeanClassses = new ArrayList<String>();

    private final String SCAN_PACKAGE = "scanPackage";

    public BeanDefinitionReader(String... locations) {
        //加载配置文件
        doLoadConfig(locations);
        //扫描包里面所有的类
        doScanner(config.getProperty(SCAN_PACKAGE));

    }



    public List<String> loadBeanDefinitions() {
        return registyBeanClassses;
    }

    public BeanDefinition registerBean(String className) {
        if (this.registyBeanClassses.contains(className)) {
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setBeanClassName(className);
            //在Ioc中的名字
            beanDefinition.setFactoryBeanName(lowerFirstCase(className.substring(className.lastIndexOf(".")+1)));
            return beanDefinition;
        }
        return null;
    }

    public Properties getConfig() {
        return this.config;
    }

    private void doLoadConfig(String... locations) {
        //加载配置文件
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(locations[0].replace("classpath:",""));
        try {
            config.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource(scanPackage.replace(".", "/"));
        File classDir = new File(url.getFile());
        for (File file:classDir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(scanPackage+"."+file.getName());
            }else {
                registyBeanClassses.add(scanPackage+"."+file.getName().replace(".class",""));
            }
        }
    }

    private String lowerFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] +=32;
        return String.valueOf(chars);
    }
}
