package com.gasyz.pattern.adapter;

/**
 * Created by gaoang on 2018/3/20.
 */
public class AdapterTest {
    public static void main(String[] args) {
        NewBankCardService service = new NewBankCardService();
        service.realNameAndBindBankCard("yy","123","123456","131232");
    }
}
