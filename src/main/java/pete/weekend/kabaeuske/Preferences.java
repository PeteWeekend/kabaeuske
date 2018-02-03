package pete.weekend.kabaeuske;

public class Preferences {

    private static Preferences instance = new Preferences();

    private Preferences() {
    }

    public static synchronized Preferences get() {
        if (instance == null) {
            instance = new Preferences();
        }
        return instance;
    }

    public int padding() {
        return 5;
    }
    public int seatWidth() {
        return 20;
    }
    public int seatHeight() {
        return 30;
    }

}
