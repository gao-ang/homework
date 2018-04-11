package com.gasyz.gamybatis.v2.configuration;

import com.gasyz.gamybatis.v2.mapper.UserMapper;
import lombok.Data;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by gaoang on 2018/4/10.
 */
@Data
public class MapperData<T> {
    private String sql;
    private Class<T> type;

    public MapperData(String sql, Class<T> type) {
        this.sql = sql;
        this.type = type;
    }

}
