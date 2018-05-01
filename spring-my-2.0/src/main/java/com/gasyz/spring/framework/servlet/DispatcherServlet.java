package com.gasyz.spring.framework.servlet;

import com.gasyz.spring.framework.context.ApplicationContext;
import com.gasyz.spring.framework.webmvc.HandlerAdapter;
import com.gasyz.spring.framework.webmvc.HandlerMapping;
import com.gasyz.spring.framework.webmvc.ModelAndView;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {

    private  final String LOCATION = "contextConfigLocation";

    private List<HandlerMapping> handlerMappings = new ArrayList<>();
    private List<HandlerAdapter> handlerAdapters = new ArrayList<>();
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
        doDispatch(req, resp);
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
        HandlerMapping handler = getHandler(req);
        HandlerAdapter ha = getHandlerAdapter(handler);
        ModelAndView mv = ha.handle(req,resp,handler);
        processDispatchResult(resp,mv);
    }

    private void processDispatchResult(HttpServletResponse resp, ModelAndView mv) {
    }

    private HandlerAdapter getHandlerAdapter(HandlerMapping handler) {
        return null;
    }

    private HandlerMapping getHandler(HttpServletRequest req) {
        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ApplicationContext applicationContext = new ApplicationContext(config.getInitParameter(LOCATION));

        initStrategies(applicationContext);
        //url匹配方法
        initHandlerMappings(applicationContext);
        //
        initHandlerAdapters(applicationContext);

        initViewResolvers(applicationContext);
    }

    private void initViewResolvers(ApplicationContext applicationContext) {
    }

    private void initHandlerAdapters(ApplicationContext applicationContext) {
    }

    private void initHandlerMappings(ApplicationContext applicationContext) {
    }

    private void initStrategies(ApplicationContext applicationContext) {
    }
}
