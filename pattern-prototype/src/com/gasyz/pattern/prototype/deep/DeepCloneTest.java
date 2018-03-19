package com.gasyz.pattern.prototype.deep;

public class DeepCloneTest {
    public static void main(String[] args) throws Exception {
        Person person = new Person();
        Telphone telphone = new Telphone();
        telphone.phoneNum = "123456";
        telphone.price = 5000;
        person.telphone = telphone;

        Person clone = (Person)person.deepClone();
        //结果为false
        System.out.println(person.telphone==clone.telphone);
    }
}
