package com.gasyz.spring.api;

import com.gasyz.spring.annotation.Autowired;
import com.gasyz.spring.annotation.Controller;
import com.gasyz.spring.annotation.RequestMapping;
import com.gasyz.spring.annotation.RequestParam;
import com.gasyz.spring.user.service.UserService;
import com.gasyz.spring.user.service.UserServiceImpl;

/**
 * Created by gaoang on 2018/4/28.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping("/get")
    public Object getUser(@RequestParam("id") Long id) {
        userServiceImpl.getUser(id);
        return null;
    }

}
