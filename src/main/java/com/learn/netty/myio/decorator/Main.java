package com.learn.netty.myio.decorator;

public class Main {
    public static void main(String[] args) {
        ConcreteDecorator1 concreteDecorator1 = new ConcreteDecorator1(new ConcreteDecorator2(new ConcreteComponent()));
        concreteDecorator1.doSomething();
    }
}
