package at.eliastrummer.pattern.observer.example;

import lombok.SneakyThrows;

import java.util.Queue;

public class WeatherDataConsumer extends WeatherDataSubject implements Runnable {
    private Queue<WeatherData> queue;
    private WeatherData weatherdata;

    public WeatherDataConsumer(Queue<WeatherData> queue) {
        this.queue = queue;
    }

    @Override
    public void notifyObservers() {
        observers.forEach(o -> o.update(weatherdata));
    }

    @SneakyThrows
    @Override
    public void run() {
        while(!Thread.interrupted()) {
            synchronized (queue) {
                try {
                    queue.wait();
                }catch (Exception e) {
                    break;
                }
                weatherdata = queue.poll();
            }

            notifyObservers();
        }
    }
}
