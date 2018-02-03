package pete.weekend.kabaeuske.model;

import pete.weekend.kabaeuske.view.SeatView;

public class Seat {

    public SeatView seatView;
    public int row;
    public int col;

    public Table table;
    public String number;
    public boolean forFree;
    public boolean child;

    public Seat(Table table, String number) {
        this.table = table;
        this.number = number;
    }

    public Reservation reservation;
    public boolean currentReservationPossible;

    public void setCurrentReservationPossible(boolean currentReservationPossible) {
        this.currentReservationPossible=currentReservationPossible;
        seatView.setSelectable(currentReservationPossible);
    }

    public void setSeatView(SeatView seatView) {
        this.seatView = seatView;
    }
}
