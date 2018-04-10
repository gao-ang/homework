package com.gasyz.gamybatis.v2.configuration;

import lombok.Data;

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
