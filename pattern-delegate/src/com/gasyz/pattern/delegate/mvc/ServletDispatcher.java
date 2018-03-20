package com.gasyz.pattern.delegate.mvc;

import com.gasyz.pattern.delegate.mvc.controller.UserController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaoang on 2018/3/20.
 */
public class ServletDispatcher {

    private List<Hanlder> hanlderMapping = new ArrayList<>();

    public ServletDispatcher() throws Exception {
        Class<UserController> userControllerClass = UserController.class;
        hanlderMapping.add(new Hanlder()
                .setController(userControllerClass.newInstance())
                .setMethod(userControllerClass.getMethod("getUserById",new Class[]{Long.class}))
                .setUrl("/user/getUserById.do"));
    }

    public void doService(HttpServletRequest request, HttpServletResponse response)throws Exception {
        doDispatch(request,response);
    }

    private void doDispatch(HttpServletRequest request,HttpServletResponse response) throws Exception{
        //1/获取用户请求的url
        String uri = request.getRequestURI();
        //2 根据用户请求的URL，去找到这个url对应的某一个java类的方法
        Hanlder hanlder = null;
        for (Hanlder h:hanlderMapping) {
            if (uri.equals(h.getUrl())) {
                hanlder = h;
                break;
            }
        }
        Object object = hanlder.getMethod().invoke(hanlder.getController(),request.getParameter("mid"));

    }


    class Hanlder{
        private Object controller;
        private Method method;
        private String url;

        public Object getController() {
            return controller;
        }

        public Hanlder setController(Object controller) {
            this.controller = controller;
            return this;
        }

        public Method getMethod() {
            return method;
        }

        public Hanlder setMethod(Method method) {
            this.method = method;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public Hanlder setUrl(String url) {
            this.url = url;
            return this;
        }
    }
}
