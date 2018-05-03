package com.gasyz.spring.framework.webmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;

//解耦，专人干专事
public class HandlerAdapter {
    private Map<String,Integer> paramMapping;

    public HandlerAdapter(Map<String,Integer> paramMapping) {
        this.paramMapping = paramMapping;
    }

    /**
     *
     * @param req
     * @param resp 只是为了将其赋值给方法参数，仅此而已
     * @param handler 包含了controller,method,url信息
     * @return
     */
    public ModelAndView handle(HttpServletRequest req, HttpServletResponse resp, HandlerMapping handler) throws InvocationTargetException, IllegalAccessException {
        //根据用户请求的参数信息，跟method中的参数信息进行动态匹配
        Class<?>[] parameterTypes = handler.getMethod().getParameterTypes();
        Map<String, String[]> parameterMap = req.getParameterMap();

        Object[] paramValues = new Object[parameterTypes.length];
        for (Map.Entry<String,String[]> param:parameterMap.entrySet()) {
            String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll("\\s", ",");
            if (!this.paramMapping.containsKey(param.getKey())) {continue;}
            Integer index = this.paramMapping.get(param.getKey());
            paramValues[index] = caseStringValue(value, parameterTypes[index]);
        }
        if(this.paramMapping.containsKey(HttpServletRequest.class.getName())) {
            Integer reqIndex = this.paramMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = req;
        }
        if(this.paramMapping.containsKey(HttpServletResponse.class.getName())) {
            Integer respIndex = this.paramMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex] = resp;
        }

        Object result = handler.getMethod().invoke(handler.getController(), paramValues);

        if (result == null) {
            return null;
        }
        boolean isModelAndView = handler.getMethod().getReturnType() == ModelAndView.class;
        if (isModelAndView) {
            return (ModelAndView)result;
        }else {
            return null;
        }
    }

    private Object caseStringValue(String value,Class<?> clazz) {
        if (clazz == String.class) {
            return value;
        } else if (clazz == Integer.class) {
            return Integer.valueOf(value);
        }else {
            return null;
        }
    }
}
