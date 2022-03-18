package at.eliastrummer.pattern.observer;

public class ConcreteSubject extends Subject {
    private String data;

    @Override
    public void notifyObservers() {
        observers.forEach(o -> o.update(data));
    }
}