package com.gasyz.spring.user.service;

import com.gasyz.spring.annotation.Service;

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
