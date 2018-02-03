package pete.weekend.kabaeuske.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Staging {

    public LocalDateTime date;
    public String name; // like 'premiere' etc

    public Seating seating;

    public List<Reservation> reservations = new ArrayList<>();


    public void clearPossibleReservations() {
        seating.tables.forEach(t->{t.clearPossibleReservations();});
    }
}
