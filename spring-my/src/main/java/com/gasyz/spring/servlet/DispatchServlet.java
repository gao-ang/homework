package com.gasyz.spring.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
    }

    private void initHandlerMapping() {
    }

    private void doRegistry() {
    }

    private void doAutowired() {

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
}
