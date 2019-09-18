package com.learn.netty.myio.decorator;

public class ConcreteComponent implements Component {
    @Override
    public void doSomething() {
        System.out.println("ConcreteComponent");
    }
}
