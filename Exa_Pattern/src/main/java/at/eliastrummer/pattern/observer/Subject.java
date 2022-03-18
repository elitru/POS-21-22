package at.eliastrummer.pattern.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    protected List<Observer> observers = new ArrayList<>();

    public void subscribe(Observer observer) {
        if (observers.contains(observer)) {
            return;
        }

        observers.add(observer);
    }

    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    public abstract void notifyObservers();
}
