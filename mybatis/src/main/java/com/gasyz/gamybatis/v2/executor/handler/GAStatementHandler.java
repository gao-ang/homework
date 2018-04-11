package com.gasyz.gamybatis.v2.executor.handler;

import com.gasyz.gamybatis.v2.configuration.MapperData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * Created by gaoang on 2018/4/10.
 */
public class GAStatementHandler {

    private GAParameterHandler gaParameterHandler;

    private GAResultHandler gaResultHandler;

    public GAStatementHandler() {
        this.gaParameterHandler = new GAParameterHandler();
        this.gaResultHandler = new GAResultHandler();
    }

    public <E> List<E> query(MapperData mapperData, Object[] parameter) throws Exception {
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(mapperData.getSql());
        gaParameterHandler.setParameters(pstmt,parameter);//封装参数
        pstmt.execute();
        return gaResultHandler.handler(pstmt,mapperData.getType());
    }


    public Connection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://47.98.133.87:3306/fruit?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "AABd37TdizrlCzlJL8x!");
        return connection;
    }
}
