package pete.weekend.kabaeuske.control;

import pete.weekend.kabaeuske.model.Seat;
import pete.weekend.kabaeuske.model.Staging;

import java.util.List;
import java.util.stream.Collectors;

public class SeatFinder {

    public static void findAndMarkFreeSeats(Staging s, int number) {
        //System.out.println("Check " + s.date);

        s.seating.tables.forEach(
                table -> {
                    //System.out.println("Check table: " + table.name + "for " + number);
                    List<Seat> free = table.seats.stream().filter(c -> {
                        return c.reservation == null;
                    }).collect(Collectors.toList());
                    //System.out.println("free seats:" + free.size());
                    if (free.size() >= number) {

                        free.forEach(seat ->
                        {
                            seat.setCurrentReservationPossible(true);
                            //System.out.println("Res pos at table:" + table.name + "seat:" + seat.number);

                        });
                    }
                }
        );

    }
}
