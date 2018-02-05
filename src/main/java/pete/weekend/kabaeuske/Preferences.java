package pete.weekend.kabaeuske;

import javafx.geometry.Insets;
import javafx.scene.text.Font;

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
        return 40;
    }
    public int seatHeight() {
        return 60;
    }

    public Insets insets() {
        return new Insets(5,5,5,5);
    }

    public double spacing() {
        return 5;
    }

    public Font fontBig() {
        return new Font("Arial", 24);
    }
}
