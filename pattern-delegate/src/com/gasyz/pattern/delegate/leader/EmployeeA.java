package com.gasyz.pattern.delegate.leader;

/**
 * Created by gaoang on 2018/3/20.
 */
public class EmployeeA implements IEmployee {
    @Override
    public void doing(String command) {
        System.out.println(command+"：员工A擅长做框架");
    }
}
