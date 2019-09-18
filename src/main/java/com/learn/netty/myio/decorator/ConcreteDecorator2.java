package com.learn.netty.myio.decorator;

public class ConcreteDecorator2 extends Decorator {
    public ConcreteDecorator2(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        this.doAnotherThing();
    }
    public void doAnotherThing(){
        System.out.println("concreteDecorator2");
    }
}
