package com.learn.netty.myio.decorator;

public class ConcreteDecorator1 extends Decorator {

    public ConcreteDecorator1(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        this.doAnotherthing();
    }
    public void doAnotherthing(){
        System.out.println("ConcreteDecorator1");
    }
}
