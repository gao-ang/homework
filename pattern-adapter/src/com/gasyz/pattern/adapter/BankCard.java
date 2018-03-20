package com.gasyz.pattern.adapter;

/**
 * Created by gaoang on 2018/3/20.
 */
public class BankCard {
    private String realName;
    private String idCardNo;
    private String BankCardNo;
    private String telphone;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        idCardNo = idCardNo;
    }

    public String getBankCardNo() {
        return BankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        BankCardNo = bankCardNo;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }
}
