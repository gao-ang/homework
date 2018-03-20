package com.gasyz.pattern.template.jdbcTemplate;

import com.gasyz.pattern.template.jdbcTemplate.dao.MemberDao;

public class MeberDaoTest {

    public static void main(String[] args) {
        MemberDao memberDao = new MemberDao(null);
        memberDao.query();
    }
}
