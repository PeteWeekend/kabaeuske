package pete.weekend.kabaeuske.model;

import java.util.ArrayList;
import java.util.List;

public class Table {

    public String name;

    public int row;
    public int col;

    public Table(String name) {
        this.name = name;
    }

    public List<Seat> seats = new ArrayList<>();

    public void clearPossibleReservations() {
        seats.forEach(t->{t.setCurrentReservationPossible(false);});
    }

}