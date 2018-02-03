package pete.weekend.kabaeuske;

import pete.weekend.kabaeuske.model.Seat;
import pete.weekend.kabaeuske.model.Seating;
import pete.weekend.kabaeuske.model.Table;

public class Elisabeth {
    public static Seating createDefaultSeating() {

        Seating seating = new Seating();
        seating.tables.add(createTable("1", 0, 0, 8));
        seating.tables.add(createTable("2", 0, 1, 8));
        seating.tables.add(createTable("3", 0, 2, 8));
        seating.tables.add(createTable("4", 0, 3, 8));
        seating.tables.add(createTable("5", 1, 0, 7));
        seating.tables.add(createTable("6", 1, 1, 9));
        seating.tables.add(createTable("7", 1, 2, 9));
        seating.tables.add(createTable("8", 1, 3, 8));
        seating.tables.add(createTable("9", 2, 0, 6));
        seating.tables.add(createTable("10", 2, 1, 7));
        seating.tables.add(createTable("11", 2, 2, 7));
        seating.tables.add(createTable("12", 2, 3, 6));
        seating.tables.add(createTable("13", 3, 0, 6));
        seating.tables.add(createTable("14", 3, 1, 8));
        seating.tables.add(createTable("15", 3, 2, 8));
        seating.tables.add(createTable("16", 4, 0, 6));
        seating.tables.add(createTable("17", 4, 1, 8));
        seating.tables.add(createTable("18", 4, 2, 8));
        return seating;

    }

    private static Table createTable(String number, int row, int col, int numSeats) {
        Table table = new Table(number);
        table.col = col;
        table.row = row;
        for (int i = 0; i < numSeats; i++) {
            table.seats.add(createSeat(table, i));
        }
        return table;
    }

    private static Seat createSeat(Table t, int i) {
        Seat seat = new Seat(t,""+i);
        if (i==0) {
            seat.row = 0;
            seat.col = 0;
        } else if (i==1) {
            seat.row = 0;
            seat.col = 1;
        } else if (i==2) {
            seat.row = 0;
            seat.col = 2;
        } else if (i==3) {
            seat.row = 1;
            seat.col = 0;
        } else if (i==4) {
            seat.row = 1;
            seat.col = 2;
        } else if (i==5) {
            seat.row = 2;
            seat.col = 0;
        } else if (i==6) {
            seat.row = 2;
            seat.col = 1;
        } else if (i==7) {
            seat.row = 2;
            seat.col = 2;
        } else {
            seat.row = 0;
            seat.col = 3;
        }
        return seat;
    }
}
