package com.gasyz.spring.demo.api;


import com.gasyz.spring.demo.user.impl.UserServiceImpl;
import com.gasyz.spring.framework.annotation.Autowired;
import com.gasyz.spring.framework.annotation.Controller;
import com.gasyz.spring.framework.annotation.RequestMapping;
import com.gasyz.spring.framework.annotation.RequestParam;
import com.gasyz.spring.framework.webmvc.ModelAndView;

/**
 * Created by gaoang on 2018/4/28.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping("/get")
    public ModelAndView getUser(@RequestParam("id") Long id) {
        userServiceImpl.getUser(id);
        return null;
    }

}
