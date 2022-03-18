package at.eliastrummer.pattern.observer.example;

public class WeatherDataPrinter implements WeatherDataObserver {
    @Override
    public void update(WeatherData weatherdata) {
        System.out.println(weatherdata);
    }
}
