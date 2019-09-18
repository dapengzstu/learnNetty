package interview.model.listener._01;

public class Main {
    public static void main(String[] args) {
         Observer o1 = new ConcreteObserver();
         Observer o2 = new ConcreteObserver();
         Observer o3 = new ConcreteObserver();
         Subject subject = new ConcreteSubject();
         subject.attach(o1);
         subject.attach(o2);
         subject.attach(o3);
         subject.notify("Hello");

    }
}
