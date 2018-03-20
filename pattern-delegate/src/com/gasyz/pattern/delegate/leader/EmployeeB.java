package com.gasyz.pattern.delegate.leader;

/**
 * Created by gaoang on 2018/3/20.
 */
public class EmployeeB implements IEmployee{
    @Override
    public void doing(String command) {
        System.out.println(command+"：员工B擅长编代码");
    }
}
