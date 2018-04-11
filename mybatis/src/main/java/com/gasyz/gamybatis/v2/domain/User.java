package com.gasyz.gamybatis.v2.domain;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Long id;

    private String username;

    private String password;

    private String telphone;

    private Date gmtCreate;

    private Date gmtModified;
}