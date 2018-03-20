package com.gasyz.pattern.decorator;

public class DecoratorTest {
    public static void main(String[] args) {
        PersonA personA = new PersonA();
        personA.skills();

        PersonDecorator personDecorator = new PersonDecorator(personA);
        personDecorator.getAnotherSkills();
    }
}
