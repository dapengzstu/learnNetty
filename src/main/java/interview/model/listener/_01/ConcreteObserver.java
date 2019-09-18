package interview.model.listener._01;

public class ConcreteObserver implements Observer{
    public void update(String string){
        System.out.println(string);
    }
}
