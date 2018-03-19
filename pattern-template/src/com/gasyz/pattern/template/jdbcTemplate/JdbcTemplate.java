package com.gasyz.pattern.template.jdbcTemplate;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.List;

public class JdbcTemplate {
    
    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<?> executeQuery(String sql, RowMapper<?> rowMapper, Object [] values) {
        try {
            //1.创建连接
            Connection conn = this.getConnection();
            //2、创建语句集
            PreparedStatement pstmt = this.createPreparedStatement(conn, sql);
            //3、执行语句集，并且获得结果集
            ResultSet rs = this.executeQuery(pstmt, values);
            //4、解析结果集
            List<?> result = this.parseREsultSEt(rs, rowMapper);
            //5、关闭结果集
            this.closeResultSet(rs);
            //6、关闭语句集
            this.closeStatement(pstmt);
            //7、关闭连接
            this.closeConnection(conn);
            return result;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
    
    private PreparedStatement createPreparedStatement(Connection conn,String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    private ResultSet executeQuery(PreparedStatement pstmt,Object [] values) throws SQLException {
        for (int i = 0;i<values.length;i++) {
            pstmt.setObject(i,values[i]);
        }
        return pstmt.executeQuery();
    }

    private List<?> parseREsultSEt(ResultSet rs,RowMapper rowMapper) {
        return null;
    }


    private void closeStatement(Statement stmt) throws  Exception{
        stmt.close();
    }

    private void closeResultSet(ResultSet rs) throws  Exception{
        rs.close();
    }

    private void closeConnection(Connection conn) throws  Exception{
        conn.close();
    }

}
