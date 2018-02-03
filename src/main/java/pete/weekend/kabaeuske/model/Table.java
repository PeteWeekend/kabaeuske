package pete.weekend.kabaeuske.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public int numOfFreeSeats() {
        List<Seat> free = this.seats.stream().filter(c -> {
            return c.reservation == null;
        }).collect(Collectors.toList());
        return free.size();
    }

}