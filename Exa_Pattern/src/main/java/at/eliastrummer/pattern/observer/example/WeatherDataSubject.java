package at.eliastrummer.pattern.observer.example;

import java.util.ArrayList;
import java.util.List;

public abstract class WeatherDataSubject {
    protected List<WeatherDataObserver> observers = new ArrayList<>();

    public void register(WeatherDataObserver observer) {
        if (observers.contains(observer)) {
            return;
        }

        observers.add(observer);
    }

    public void unregister(WeatherDataObserver observer) {
        observers.remove(observer);
    }

    public abstract void notifyObservers();
}
