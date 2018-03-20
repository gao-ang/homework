package com.gasyz.pattern.decorator;

/**
 * Created by gaoang on 2018/3/20.
 */
public class PersonDecorator implements IPersonDecorator {
    private Person person;
    public PersonDecorator(Person person) {
        this.person = person;
    }
    @Override
    public void skills() {
        person.skills();
    }

    @Override
    public void getAnotherSkills() {
        System.out.println("踢足球");
    }
}
