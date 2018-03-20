package com.gasyz.pattern.adapter;

/**
 * Created by gaoang on 2018/3/20.
 */
public class BankCardService {
    public void realName(String realName,String idCardNo) {
        System.out.println("实名认证");
    }

    public void bindBankCard(String bankCardNo,String telphone) {
        System.out.println("绑定银行卡");
    }
}
