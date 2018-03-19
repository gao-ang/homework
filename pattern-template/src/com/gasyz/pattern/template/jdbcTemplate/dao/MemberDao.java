package com.gasyz.pattern.template.jdbcTemplate.dao;

import com.gasyz.pattern.template.jdbcTemplate.JdbcTemplate;
import com.gasyz.pattern.template.jdbcTemplate.RowMapper;
import com.gasyz.pattern.template.jdbcTemplate.entity.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MemberDao {
    private JdbcTemplate jdbcTemplate= new JdbcTemplate(null);

    public List<?> query(){
        String sql = "select * from t_member";
        return jdbcTemplate.executeQuery(sql,new RowMapper<Member>(){
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member();
                member.setUsername(rs.getString("username"));
                member.setPassword(rs.getString("password"));
                member.setAge(rs.getInt("age"));
                return member;
            }
        },null);
    }

}
