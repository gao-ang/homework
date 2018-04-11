package com.gasyz.gamybatis.v2;


import com.gasyz.gamybatis.v2.configuration.GAConfiguration;
import com.gasyz.gamybatis.v2.domain.User;
import com.gasyz.gamybatis.v2.executor.impl.SimpleGAExecutor;
import com.gasyz.gamybatis.v2.mapper.UserMapper;
import com.gasyz.gamybatis.v2.session.GASqlSession;

import java.util.List;

/**
 * Created by gaoang on 2018/4/10.
 */
public class App {
    public static void main(String[] args) throws NoSuchMethodException {
        SimpleGAExecutor simpleGAExecutor = new SimpleGAExecutor();
        GAConfiguration gaConfiguration = new GAConfiguration("com.gasyz.gamybatis.v2.mapper");
        GASqlSession gaSqlSession = new GASqlSession(gaConfiguration,simpleGAExecutor);
        UserMapper mapper = gaSqlSession.getMapper(UserMapper.class);
        List<User> user = mapper.selectAllUser();
        System.out.println(user);
    }
}
