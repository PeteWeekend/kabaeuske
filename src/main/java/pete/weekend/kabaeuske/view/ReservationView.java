package pete.weekend.kabaeuske.view;

import pete.weekend.kabaeuske.Preferences;
import pete.weekend.kabaeuske.control.SeatFinder;
import pete.weekend.kabaeuske.model.Reservation;
import pete.weekend.kabaeuske.model.Staging;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

import java.time.LocalDateTime;

public class ReservationView {

    Staging staging;

    private ObservableList<Reservation> reservations = FXCollections.observableArrayList();


    private int currentFreeFind;
    private String currentReservationName = "";

    public ReservationView(Staging staging) {
        this.staging = staging;
        reservations.addAll(staging.reservations);
    }

    public Node createView() {
        VBox vBox = new VBox();

        vBox.setSpacing(Preferences.get().spacing());
        vBox.setPadding(Preferences.get().insets());
        HBox table = tableAndButtons();
        table.setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(table, Priority.ALWAYS);

        vBox.getChildren().add(table);

        vBox.getChildren().add(newReservationInput());

        return vBox;
    }

    private Node newReservationInput() {

        VBox vBox = new VBox();

        HBox hBox = new HBox();
        hBox.setSpacing(Preferences.get().spacing());
        hBox.setPadding(Preferences.get().insets());
        hBox.getChildren().add(new Label("Neu"));
        TextField name = new TextField(currentReservationName);
        name.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(name, Priority.ALWAYS);
        hBox.getChildren().add(name);
        name.setOnKeyReleased(e -> {
            currentReservationName = name.getText();
        });
        ChoiceBox cb = new ChoiceBox();
        cb.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8");
        cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number index) {
                currentFreeFind = Integer.valueOf((String) cb.getItems().get((Integer) index));
                staging.clearPossibleReservations();
                SeatFinder.findAndMarkFreeSeats(staging, currentFreeFind);
            }
        });
        hBox.getChildren().add(cb);
        vBox.getChildren().add(hBox);


        vBox.getChildren().add(labels());
        vBox.getChildren().add(new Line(0, 0, 200, 0));
        vBox.getChildren().add(totals());


        return vBox;

    }

    private HBox labels() {
        HBox hBoxLabels = new HBox();
        hBoxLabels.setSpacing(Preferences.get().spacing());
        hBoxLabels.setPadding(Preferences.get().insets());
        HBox hBoxLabelsLeft = new HBox();
        hBoxLabelsLeft.getChildren().add(new Label("Max Anzahl zusammenh. Plätze:"));
        hBoxLabelsLeft.getChildren().add(new Label("-"));
        hBoxLabelsLeft.setAlignment(Pos.CENTER_LEFT);

        hBoxLabels.getChildren().add(hBoxLabelsLeft);
        HBox hBoxLabelsRight = new HBox();

        hBoxLabelsRight.getChildren().add(new Label("Noch auszuwählen:"));
        hBoxLabelsRight.getChildren().add(new Label("-"));
        hBoxLabelsRight.setAlignment(Pos.CENTER_RIGHT);
        hBoxLabelsRight.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(hBoxLabelsRight, Priority.ALWAYS);

        hBoxLabels.getChildren().add(hBoxLabelsRight);
        return hBoxLabels;
    }

    private HBox totals() {
        HBox hBoxLabels = new HBox();
        hBoxLabels.setSpacing(Preferences.get().spacing());
        hBoxLabels.setPadding(Preferences.get().insets());
        HBox hBoxLabelsLeft = new HBox();
        hBoxLabelsLeft.getChildren().add(new Label("Gesamtzahl Plätze:"));
        hBoxLabelsLeft.getChildren().add(new Label("-"));
        hBoxLabelsLeft.setAlignment(Pos.CENTER_LEFT);

        hBoxLabels.getChildren().add(hBoxLabelsLeft);
        HBox hBoxLabelsRight = new HBox();

        hBoxLabelsRight.getChildren().add(new Label("Summe reserviert:"));
        hBoxLabelsRight.getChildren().add(new Label("-"));
        hBoxLabelsRight.setAlignment(Pos.CENTER_RIGHT);
        hBoxLabelsRight.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(hBoxLabelsRight, Priority.ALWAYS);

        hBoxLabels.getChildren().add(hBoxLabelsRight);
        return hBoxLabels;
    }

    private HBox tableAndButtons() {

        HBox hBox = new HBox();
        javafx.scene.control.TableView<Reservation> tableView = new TableView();

        TableColumn<Reservation, String> reservation = new TableColumn("Reservierung");
        reservation.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Reservation, String> table = new TableColumn("Tische");
        table.setCellValueFactory(new PropertyValueFactory<>("tableNumbers"));

        TableColumn<Reservation, Integer> seats = new TableColumn("Plätze");
        seats.setCellValueFactory(new PropertyValueFactory<>("seatNumbers"));

        TableColumn<Reservation, LocalDateTime> reservedAt = new TableColumn("reserviert am");
        reservedAt.setCellValueFactory(new PropertyValueFactory<>("reservedAt"));

        tableView.getColumns().addAll(reservation, table, seats, reservedAt);

        tableView.setItems(reservations);
        tableView.setPlaceholder(new Label("Keine Reservierungen"));
        tableView.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(tableView, Priority.ALWAYS);
        hBox.getChildren().add(tableView);
        VBox buttons = new VBox();
        Image seatFree = new Image("/trash.png", 32, 32, false, false, false);
        ImageView iv = new ImageView(seatFree);
        Button deleteButton = new Button("", iv);
        buttons.getChildren().add(deleteButton);
        hBox.getChildren().add(buttons);
        return hBox;

    }
}
