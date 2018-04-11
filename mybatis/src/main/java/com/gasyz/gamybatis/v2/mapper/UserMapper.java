package com.gasyz.gamybatis.v2.mapper;

import com.gasyz.gamybatis.v2.annotation.SelectSql;
import com.gasyz.gamybatis.v2.domain.User;

import java.util.List;

/**
 * Created by gaoang on 2018/4/10.
 */
public interface UserMapper {
    @SelectSql("select * from user where id =?")
    User selectByPrimaryKey(Long userId);

    @SelectSql("select * from user")
    List<User> selectAllUser();
}
