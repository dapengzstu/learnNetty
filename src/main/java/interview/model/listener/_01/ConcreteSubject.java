package interview.model.listener._01;

import java.util.HashSet;
import java.util.Set;

public class ConcreteSubject implements Subject {

    private static Set<Observer> observers = new HashSet<Observer>();

    @Override
    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void delet(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notify(String message) {
        for (Observer ob :
                observers) {
            ob.update(message);
        }
    }
}
