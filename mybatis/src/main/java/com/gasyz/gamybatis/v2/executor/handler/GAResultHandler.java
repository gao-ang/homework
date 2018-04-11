package com.gasyz.gamybatis.v2.executor.handler;

import com.gasyz.gamybatis.v2.configuration.MapperData;
import com.google.common.base.CaseFormat;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gaoang on 2018/4/10.
 */
public class GAResultHandler {

    public <E> List<E> handler(Statement stmt,Class type) throws Exception {
        ResultSet rs = stmt.getResultSet();
        List<Object> resultObjs = new ArrayList();
        while (rs.next()) {
            Object resultObj = new DefaultObjectFactory().create(type);
            for (Field field : resultObj.getClass().getDeclaredFields()) {
                setValue(resultObj, field, rs );
            }
            resultObjs.add(resultObj);
        }
        return (List<E>)resultObjs;
    }

    private void setValue(Object resultObj, Field field, ResultSet rs) throws NoSuchMethodException, SQLException, InvocationTargetException, IllegalAccessException {
        Method setMethod = resultObj.getClass().getMethod("set" + upperCapital(field.getName()), field.getType());
        setMethod.invoke(resultObj, getResult(field,rs));
    }

    private Object getResult(Field field, ResultSet rs) throws SQLException {
        //TODO type handles
        Class<?> type = field.getType();
        String name = javaNameToSqlName(field.getName());
        if(Integer.class == type){
            return rs.getInt(name);
        }
        if(String.class == type){
            return rs.getString(name);
        }
        if (Long.class == type) {
            return rs.getLong(name);
        }
        if (Date.class == type) {
            return rs.getDate(name);
        }
        return rs.getObject(name);
    }

    private String upperCapital(String name) {
        String first = name.substring(0, 1);
        String tail = name.substring(1);
        return first.toUpperCase() + tail;
    }

    private String javaNameToSqlName(String name) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,name);
    }
}
