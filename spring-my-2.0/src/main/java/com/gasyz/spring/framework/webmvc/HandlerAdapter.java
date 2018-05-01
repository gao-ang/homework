package com.gasyz.spring.framework.webmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//解耦，专人干专事
public class HandlerAdapter {

    /**
     *
     * @param req
     * @param resp 只是为了将其赋值给方法参数，仅此而已
     * @param handler 包含了controller,method,url信息
     * @return
     */
    public ModelAndView handle(HttpServletRequest req, HttpServletResponse resp, HandlerMapping handler) {
        //根据用户请求的参数信息，跟method中的参数信息进行动态匹配


        return null;
    }
}
