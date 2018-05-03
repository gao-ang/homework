package com.gasyz.spring.framework.servlet;

import com.gasyz.spring.framework.annotation.Controller;
import com.gasyz.spring.framework.annotation.RequestMapping;
import com.gasyz.spring.framework.context.ApplicationContext;
import com.gasyz.spring.framework.webmvc.HandlerAdapter;
import com.gasyz.spring.framework.webmvc.HandlerMapping;
import com.gasyz.spring.framework.webmvc.ModelAndView;
import com.gasyz.spring.framework.webmvc.ViewResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DispatcherServlet extends HttpServlet {

    private  final String LOCATION = "contextConfigLocation";

    private List<HandlerMapping> handlerMappings = new ArrayList<>();
    private Map<HandlerMapping,HandlerAdapter> handlerAdapters = new HashMap<>();

    private List<ViewResolver> viewResolvers = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");
        */
        try {
            doDispatch(req, resp);
        }catch (Exception e) {
            resp.getWriter().write("500 Exception:"+ Arrays.toString(e.getStackTrace()));
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        HandlerMapping handler = getHandler(req);
        if (handler == null) {
            resp.getWriter().write("404 Exception:");
        }
        HandlerAdapter ha = getHandlerAdapter(handler);
        ModelAndView mv = ha.handle(req,resp,handler);
        processDispatchResult(resp,mv);


    }

    private void processDispatchResult(HttpServletResponse resp, ModelAndView mv) throws Exception {
        if (null == mv) {return;}
        if (this.viewResolvers.isEmpty()) {return;}

        for (ViewResolver viewResolver:viewResolvers) {
            if (!mv.getViewName().equals(viewResolver.getViewName())) {
                continue;
            }
            String out = viewResolver.viewResolver(mv);
            if (out != null) {
                resp.getWriter().write(out);
                break;
            }
        }
    }

    private HandlerAdapter getHandlerAdapter(HandlerMapping handler) {
        if (this.handlerAdapters.isEmpty())  {return null;}
        return this.handlerAdapters.get(handler);
    }

    private HandlerMapping getHandler(HttpServletRequest req) {
        if (this.handlerMappings.isEmpty()) {return  null;}
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");

        for (HandlerMapping handlerMapping:this.handlerMappings) {
            Matcher matcher = handlerMapping.getPattern().matcher(url);
            if (!matcher.matches()) {continue;}
            return handlerMapping;
        }
        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ApplicationContext applicationContext = new ApplicationContext(config.getInitParameter(LOCATION));

        initStrategies(applicationContext);
        //url匹配到Controller中的方法
        initHandlerMappings(applicationContext);
        //
        initHandlerAdapters(applicationContext);

        initViewResolvers(applicationContext);
    }

    //解决页面名字和模板文件关键的问题
    private void initViewResolvers(ApplicationContext context) {
        String templateRoot = context.getConfig().getProperty("templateRoot");//获取文件目录
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        File templateRootDir = new File(templateRootPath);
        for (File template:templateRootDir.listFiles()) {
            this.viewResolvers.add(new ViewResolver(template.getName(),template));
        }
    }

    //所有方法参数动态配置
    private void initHandlerAdapters(ApplicationContext applicationContext) {
        for (HandlerMapping handlerMapping:this.handlerMappings) {
            Map<String,Integer> paramMapping = new HashMap<>();

            //处理命名参数
            Annotation[][] pa = handlerMapping.getMethod().getParameterAnnotations();
            for (int i = 0;i<pa.length;i++) {
                for (Annotation a:pa[i]) {
                    if(a instanceof  RequestMapping) {
                        String paramName =  ((RequestMapping) a).value();
                        if (!"".equals(paramName.trim())) {
                            paramMapping.put(paramName,i);
                        }
                    }
                }
            }

            //处理非命名参数，只处理Request和Response
            Class<?>[] parameterTypes = handlerMapping.getMethod().getParameterTypes();
            for (int i=0;i<parameterTypes.length;i++) {
                Class<?> type = parameterTypes[i];
                if (type == HttpServletRequest.class || type == HttpServletResponse.class) {
                    paramMapping.put(type.getName(),i);
                }
            }

            this.handlerAdapters.put(handlerMapping,new HandlerAdapter(paramMapping));

        }
    }

    private void initHandlerMappings(ApplicationContext context) {
        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName:beanNames) {
            Object instance = context.getBean(beanName);
            Class<?> clazz = instance.getClass();
            if (clazz.isAnnotationPresent(Controller.class)) {continue;}

            String baseUrl="";

            if (clazz.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
                baseUrl = requestMapping.value();
            }

            Method[] methods = clazz.getMethods();
            for (Method method:methods) {
                if (!method.isAnnotationPresent(RequestMapping.class)) {continue;}
                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                String regex = ("/"+baseUrl+requestMapping.value().replaceAll("/+","/"));
                Pattern pattern = Pattern.compile(regex);
                this.handlerMappings.add(new HandlerMapping(pattern,instance,method));
                System.out.println("Mapping" + regex +"," +method);
            }
        }
    }

    private void initStrategies(ApplicationContext applicationContext) {
    }
}
