package com.gasyz.gamybatis.v2;


import com.gasyz.gamybatis.v2.domain.User;
import com.gasyz.gamybatis.v2.configuration.GAConfiguration;
import com.gasyz.gamybatis.v2.executor.impl.SimpleGAExecutor;
import com.gasyz.gamybatis.v2.mapper.UserMapper;
import com.gasyz.gamybatis.v2.session.GASqlSession;

/**
 * Created by gaoang on 2018/4/10.
 */
public class App {
    public static void main(String[] args) {
        SimpleGAExecutor simpleGAExecutor = new SimpleGAExecutor();
        GAConfiguration gaConfiguration = new GAConfiguration("com.gasyz.gamybatis.v2.mapper");
        GASqlSession gaSqlSession = new GASqlSession(gaConfiguration,simpleGAExecutor);
        UserMapper mapper = gaSqlSession.getMapper(UserMapper.class);
        User user = mapper.selectByPrimaryKey(1l);
        System.out.println(user);
    }
}
