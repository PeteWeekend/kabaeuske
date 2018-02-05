package pete.weekend.kabaeuske.view;

import pete.weekend.kabaeuske.Preferences;
import pete.weekend.kabaeuske.model.Seat;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class SeatView {

    Preferences pref = Preferences.get();

    Seat seat;

    public SeatView(Seat seat) {
        this.seat = seat;
    }

    ImageView seatImageView;

    public void setSelectable(boolean selectable ){
        if (selectable) {
            seatImageView.setImage(new Image("/seat_selectable.png",pref.seatWidth(), pref.seatHeight(), false, false, false));

        } else {
            Image seatFree = new Image("/seat_free.png",pref.seatWidth(), pref.seatHeight(),false, false, false);
            Image seatOcc = new Image("/seat_occ.png",pref.seatWidth(), pref.seatHeight(), false, false, false);
            seatImageView.setImage(seat.reservation == null ? seatFree:seatOcc);
        }

    }

    Node createView() {

        StackPane seatPane = new StackPane();

        seat.setSeatView(this);
        Image seatFree = new Image("/seat_free.png",pref.seatWidth(), pref.seatHeight(),false, false, false);
        Image seatOcc = new Image("/seat_occ.png",pref.seatWidth(), pref.seatHeight(), false, false, false);
        seatImageView = new ImageView(seat.reservation == null ? seatFree:seatOcc);

        Text text = new Text(seat.reservation == null ? "" : seat.reservation.getName().substring(0,2));
        text.setFill(Color.WHITE);
        seatPane.getChildren().add(seatImageView);
        seatPane.getChildren().add(text);

        seatPane.setOnMouseClicked(
                ev -> {
                    /*
                    if (seat.currentReservationPossible) {
                        if (text.getText().equals("")&&!"".equals(ControlView.getInstance().getCurrentReservationName())) {
                            text.setText(ControlView.getInstance().getCurrentReservationName());
                            selectedSeats++;
                            if (selectedSeats >= ControlView.getInstance().getCurrentFreeFind()) {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Reservierung anlegen '" + ControlView.getInstance().getCurrentReservationName() + "' ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                                        alert.showAndWait();

                                if (alert.getResult() == ButtonType.YES) {

                                    Reservation reservation = new Reservation(ControlView.getInstance().getCurrentReservationName(),
                                            ControlView.getInstance().getCurrentStaging());
                                    seat.reservation=reservation;
                                    ControlView.getInstance().addReservation(
                                            reservation);

                                }
                            }
                        } else {
                            text.setText("");
                            selectedSeats--;
                        }
                    }
                    */
                });
        return seatPane;
    }
}
