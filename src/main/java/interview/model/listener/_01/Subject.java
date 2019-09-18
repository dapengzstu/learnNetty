package interview.model.listener._01;

public interface Subject {
    void attach(Observer observer);
    void delet(Observer observer);
    void notify(String message);
}
