package pete.weekend.kabaeuske.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Reservation {

    private String name;
    private Staging staging;
    private LocalDateTime reservedAt;
    private List<Seat> seats = new ArrayList<>();

    public Reservation(String name, Staging staging) {
        this.name = name;
        this.reservedAt = LocalDateTime.now();
        this.staging = staging;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getReservedAt() {
        return reservedAt;
    }

    public void setReservedAt(LocalDateTime reservedAt) {
        this.reservedAt = reservedAt;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Staging getStaging() {
        return staging;
    }

    public void setStaging(Staging staging) {
        this.staging = staging;
    }

    public LocalDateTime getStagingDate() {
        return staging.date;
    }

    public String getTableNumbers() {
        Set<String> tableNumbers = new HashSet<>();
        seats.forEach(s -> {
            tableNumbers.add(s.table.name);
        });
        StringBuffer s = new StringBuffer();
        Iterator<String> it = tableNumbers.iterator();
        while (it.hasNext()) {
            s.append(it.next());
            if (it.hasNext()) {
                s.append(", ");
            }
            ;
        }
        return s.toString();
    }

    public String getSum() {
        return String.format("%2.2f",Math.random()*20)+"€";
    }

    public String getSeatNumbers() {
        Set<String> tableNumbers = new HashSet<>();
        seats.forEach(s -> {
            tableNumbers.add(s.table.name);
        });
        if (tableNumbers.size() > 1) {

            StringBuffer s = new StringBuffer();
            Iterator<String> it = tableNumbers.iterator();
            while (it.hasNext()) {
                String table = it.next();
                s.append(table).append(":");

                Iterator<Seat> seatIteratort = seats.iterator();
                while (seatIteratort.hasNext()) {
                    Seat seat = seatIteratort.next();
                    if (table.equals(seat.table.name)) {
                        s.append(seat.number).append(" ");
                    }
                }


                if (it.hasNext()) {
                    s.append(", ");
                }
            }

            return s.toString();


        } else {
            StringBuffer s = new StringBuffer();
            Iterator<Seat> it = seats.iterator();
            while (it.hasNext()) {
                s.append(it.next().number);
                if (it.hasNext()) {
                    s.append(", ");
                }
                ;
            }
            return s.toString();

        }
    }

}
