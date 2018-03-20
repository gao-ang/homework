package com.gasyz.pattern.delegate.leader;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaoang on 2018/3/20.
 */
public class Leader implements IEmployee{

    private Map<String,IEmployee> employees = new HashMap<String,IEmployee>();

    public Leader() {
        employees.put("架构",new EmployeeA());
        employees.put("编码",new EmployeeB());
    }

    @Override
    public void doing(String command) {
        employees.get(command).doing(command);
    }
}
