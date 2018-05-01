package com.gasyz.spring.demo.user.impl;


import com.gasyz.spring.demo.user.UserService;
import com.gasyz.spring.framework.annotation.Service;

/**
 * Created by gaoang on 2018/4/28.
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public void getUser(Long id) {
        System.out.println("用户："+id);
    }
}
