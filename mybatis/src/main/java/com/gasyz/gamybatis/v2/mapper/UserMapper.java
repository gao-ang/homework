package com.gasyz.gamybatis.v2.mapper;

import com.gasyz.gamybatis.v2.annotation.SelectSql;
import com.gasyz.gamybatis.v2.domain.User;

/**
 * Created by gaoang on 2018/4/10.
 */
public interface UserMapper {
    @SelectSql("select * from user where id =1")
    User selectByPrimaryKey(Long userId);
}
