package pete.weekend.kabaeuske;

import pete.weekend.kabaeuske.model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class DummyData {

    static Season createDummy() {
        Season season = new Season();
        season.name = "Spielzeit 20Dummy";
        season.stagings.add(staging("GeneralProbe",LocalDateTime.of(2018, 9, 18, 18, 00)));
        season.stagings.add(staging("Premiere",LocalDateTime.of(2018, 9, 20, 20, 00)));
        season.stagings.add(staging("",LocalDateTime.of(2018, 9, 21, 20, 00)));
        season.stagings.add(staging("",LocalDateTime.of(2018, 9, 22, 20, 00)));
        season.stagings.add(staging("",LocalDateTime.of(2018, 9, 23, 20, 00)));
        season.stagings.add(staging("",LocalDateTime.of(2018, 9, 25, 20, 00)));

        addDummyReservations(season);
        return season;
    }

    private static void addDummyReservations(Season season) {

        String[] names = new String[]{"Leusch","Wedekind","Drees","Icking","Jurisch","Streese","Tillmann","Teitscheidt","Schuler","Steffen","Scheid",
        "Schasting","Scheites","Wallraff",};

        season.stagings.forEach( staging -> {

            Stream.of(names).forEach( name -> {

                Reservation r = new Reservation(name,staging);

                Table t  = staging.seating.tables.get((int)(staging.seating.tables.size()*Math.random()));
                boolean dummySet=false;
                while (!dummySet) {
                    int numSeats = 1 + (int) (3 * Math.random()+(int) ((t.seats.size()-3) * Math.random()*0.66));

                    if (t.numOfFreeSeats() >= numSeats) {
                        for (int i = 0; i < numSeats; i++) {
                            Seat s = t.seats.get(i);
                            if (s.reservation == null) {
                                s.reservation = r;
                                s.reservation.getSeats().add(s);
                                s.forFree=Math.random()>0.80;
                                s.child=Math.random()>0.75;
                             }
                        }
                        dummySet=true;
                        staging.reservations.add(r);
                    }
                }

            });


        });

        /*
        Reservation r = new Reservation("Leusch",season.stagings.get(0));
        Seat s = season.stagings.get(0).seating.tables.get(0).seats.get(1);
        s.reservation = r;
        r.getSeats().add(s);
        s = season.stagings.get(0).seating.tables.get(0).seats.get(2);
        s.reservation = r;
        r.getSeats().add(s);
        s = season.stagings.get(0).seating.tables.get(0).seats.get(3);
        s.reservation = r;
        r.getSeats().add(s);
        season.stagings.get(0).reservations.add(r);

        r = new Reservation("Wedekind",season.stagings.get(0));
        s = season.stagings.get(0).seating.tables.get(1).seats.get(1);
        s.reservation = r;
        r.getSeats().add(s);
        s = season.stagings.get(0).seating.tables.get(1).seats.get(2);
        s.reservation = r;
        r.getSeats().add(s);
        s = season.stagings.get(0).seating.tables.get(2).seats.get(1);
        s.reservation = r;
        r.getSeats().add(s);
        season.stagings.get(0).reservations.add(r);

        r = new Reservation("Vogels",season.stagings.get(1));
        s = season.stagings.get(1).seating.tables.get(3).seats.get(1);
        s.reservation = r;
        r.getSeats().add(s);
        s = season.stagings.get(1).seating.tables.get(3).seats.get(2);
        s.reservation = r;
        r.getSeats().add(s);
        s = season.stagings.get(1).seating.tables.get(3).seats.get(3);
        s.reservation = r;
        r.getSeats().add(s);
        season.stagings.get(1).reservations.add(r);
        */

    }

    static Staging staging(String name, LocalDateTime date) {
        Staging staging = new Staging();
        staging.date = date;
        staging.name = name+" "+date.format(DateTimeFormatter.ISO_DATE);
        staging.seating = PBouwmansHaus.createDefaultSeating();
        return staging;
    }

}
