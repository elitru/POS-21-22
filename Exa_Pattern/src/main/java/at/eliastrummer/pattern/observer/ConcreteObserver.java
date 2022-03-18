package at.eliastrummer.pattern.observer;

public class ConcreteObserver implements Observer {
    private Subject subject;

    public ConcreteObserver(Subject subject) {
        this.subject = subject;
        this.subject.subscribe(this);
    }

    @Override
    public void update(String data) {
        System.out.println("Update retrieved: " + data);
    }
}