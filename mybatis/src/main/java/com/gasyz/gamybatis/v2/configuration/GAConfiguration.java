package com.gasyz.gamybatis.v2.configuration;

import com.gasyz.gamybatis.v2.plugin.GAInterceptor;
import com.gasyz.gamybatis.v2.plugin.GAInterceptorChain;
import com.gasyz.gamybatis.v2.session.GASqlSession;
import lombok.Data;

/**
 * Created by gaoang on 2018/4/9.
 */
@Data
public class GAConfiguration {

    private String scanPath;

    private GAMapperRegistory gaMapperRegistory;

    private final GAInterceptorChain gaInterceptorChain = new GAInterceptorChain();

    public GAConfiguration(String scanPath) {
        this.scanPath = scanPath;
        gaMapperRegistory = new GAMapperRegistory(scanPath);
    }

    public <T> T getMapper(Class<T> clazz, GASqlSession gaSqlSession) {
        return this.gaMapperRegistory.getMapper(clazz, gaSqlSession);
    }

    public void addInterceptor(GAInterceptor gaInterceptor) {
        this.gaInterceptorChain.addInterceptor(gaInterceptor);
    }
}
