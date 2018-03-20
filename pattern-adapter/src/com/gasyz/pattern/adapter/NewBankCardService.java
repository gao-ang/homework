package com.gasyz.pattern.adapter;

/**
 * Created by gaoang on 2018/3/20.
 */
public class NewBankCardService extends BankCardService {

    public void realNameAndBindBankCard(String realName,String idCardNo,String bankCardNo,String telphone) {
        super.realName(realName,idCardNo);
        super.bindBankCard(bankCardNo,telphone);
    }
}
