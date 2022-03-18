package at.eliastrummer.pattern.singleton;

public class TheOneAndOnly {
    private static TheOneAndOnly instance;

    private TheOneAndOnly() {

    }

    public static TheOneAndOnly getInstance() {
        if (instance == null) {
            instance = new TheOneAndOnly();
        }
        return instance;
    }
}