package pete.weekend.kabaeuske.view;

import pete.weekend.kabaeuske.Preferences;
import pete.weekend.kabaeuske.model.Reservation;
import pete.weekend.kabaeuske.model.Staging;
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

public class CashView {

    Staging staging;

    private ObservableList<Reservation> reservations = FXCollections.observableArrayList();;

    private int currentFreeFind;
    private String currentReservationName = "";

    public void setStaging(Staging staging) {
        reservations.clear();
        this.staging = staging;
        reservations.addAll(staging.reservations);
    }

    public Node getNode() {
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

        vBox.getChildren().add(labels());
        vBox.getChildren().add(new Line(0,0,200,0));
        vBox.getChildren().add(totals());


        return vBox;

    }

    private HBox labels() {
        HBox hBoxLabels = new HBox();
        hBoxLabels.setSpacing(Preferences.get().spacing());
        hBoxLabels.setPadding(Preferences.get().insets());
        HBox hBoxLabelsLeft = new HBox();
        hBoxLabelsLeft.getChildren().add(new Label("Anzahl noch fehlender GästeGruppen:"));
        hBoxLabelsLeft.getChildren().add(new Label("-"));
        hBoxLabelsLeft.setAlignment(Pos.CENTER_LEFT);

        hBoxLabels.getChildren().add(hBoxLabelsLeft);
        return hBoxLabels;
    }

    private HBox totals() {
        HBox hBoxLabels = new HBox();
        hBoxLabels.setSpacing(Preferences.get().spacing());
        hBoxLabels.setPadding(Preferences.get().insets());
        HBox hBoxLabelsLeft = new HBox();
        hBoxLabelsLeft.getChildren().add(new Label("Gästegruppen:"));
        hBoxLabelsLeft.getChildren().add(new Label("-"));
        hBoxLabelsLeft.setAlignment(Pos.CENTER_LEFT);

        hBoxLabels.getChildren().add(hBoxLabelsLeft);
        HBox hBoxLabelsRight = new HBox();

        hBoxLabelsRight.getChildren().add(new Label("Summe:"));
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

        TableColumn<Reservation, LocalDateTime> sum = new TableColumn("Summe");
        sum.setCellValueFactory(new PropertyValueFactory<>("sum"));

        tableView.getColumns().addAll(reservation, table, seats, sum);

        tableView.setItems(reservations);
        tableView.setPlaceholder(new Label("Keine Reservierungen"));
        tableView.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(tableView, Priority.ALWAYS);
        hBox.getChildren().add(tableView);
        VBox buttons = new VBox();
        Image payed = new Image("/ok.png",32, 32, false, false, false);
        ImageView iv = new ImageView(payed);
        Button guestPayedButton = new Button("", iv);
        buttons.getChildren().add(guestPayedButton);
        hBox.getChildren().add(buttons);
        return hBox;

    }
}
