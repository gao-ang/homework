package com.gasyz.gamybatis.v2.executor.handler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by gaoang on 2018/4/11.
 */
public class GAParameterHandler {

    public void setParameters(PreparedStatement pstmt, Object[] parameter) throws SQLException {
        if (parameter != null) {
            for (int i=0;i<parameter.length;i++) {
                pstmt.setObject(i+1,parameter[i]);
            }
        }
    }
}
