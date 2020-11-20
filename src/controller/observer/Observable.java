package controller.observer;

public interface Observable {
    void join(Observer observer);
    void leave(Observer observer);
    void message();
}
