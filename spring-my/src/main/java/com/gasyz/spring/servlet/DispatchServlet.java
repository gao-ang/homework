package com.gasyz.spring.servlet;

import com.gasyz.spring.annotation.Autowired;
import com.gasyz.spring.annotation.Controller;
import com.gasyz.spring.annotation.Service;
import com.gasyz.spring.api.UserController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by gaoang on 2018/4/28.
 */
public class DispatchServlet extends HttpServlet {

    private Properties contextConfig = new Properties();

    private Map<String,Object> beanMap = new ConcurrentHashMap<String,Object>();

    private List<String> classNames = new ArrayList<String>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("---------调用doPost--------------");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //开始初始化的过程

        //定位
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        //加载
        doScanner(contextConfig.getProperty("scanPackage"));

        //注册
        doRegistry();

        //自动依赖注入
        doAutowired();

        //如果是SpringMVC会多设计一个HandlerMapping

        //将@RequestMapping的
        initHandlerMapping();

        UserController userController = (UserController)this.beanMap.get("userController");
        userController.getUser(1l);
    }

    private void initHandlerMapping() {
    }

    private void doAutowired() {
        if (beanMap.isEmpty()) {return;}
        for (Map.Entry<String,Object> entry: beanMap.entrySet()) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field:fields) {
                if (!field.isAnnotationPresent(Autowired.class)){continue;}
                Autowired autowired = field.getAnnotation(Autowired.class);
                String beanName = autowired.value().trim();
                if ("".equals(beanName)) {
                    Class<?> clazz = field.getType();
                    if (clazz.isInterface()) {
                        beanName = clazz.getName();
                    }else {
                        beanName = field.getName();
                    }
                }
                field.setAccessible(true);
                try {
                    Object o = beanMap.get(beanName);
                    field.set(entry.getValue(),beanMap.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doRegistry() {
        if(classNames.isEmpty()) {
            return;
        }
        for (String classsName:classNames) {
            try {
                Class<?> clazz = Class.forName(classsName);

                if (clazz.isAnnotationPresent(Controller.class)) {
                    String beanName = lowerFirstCase(clazz.getSimpleName());
                    
                    beanMap.put(beanName,clazz.newInstance());
                }else if (clazz.isAnnotationPresent(Service.class)) {
                    Service service = clazz.getAnnotation(Service.class);
                    String beanName = service.value();//自定义的名字
                    if ("".equals(beanName.trim())) {
                        beanName = lowerFirstCase(clazz.getSimpleName());//默认的名字
                    }
                    Object instance = clazz.newInstance();
                    beanMap.put(beanName,instance);

                    //接口注册对应的实体类
                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class<?> i:interfaces) {
                        beanMap.put(i.getName(),instance);
                    }
                }else {
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
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
                classNames.add(scanPackage+"."+file.getName().replace(".class",""));
            }
        }
    }

    private void doLoadConfig(String location) {
        //加载配置文件
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(location.replace("classpath:",""));
        try {
            contextConfig.load(is);
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

    //首字母小写
    private String lowerFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] +=32;
        return String.valueOf(chars);
    }
}
