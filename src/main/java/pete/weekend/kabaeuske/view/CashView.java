package pete.weekend.kabaeuske.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javafx.util.Duration;
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
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Timer;
import java.util.TimerTask;

public class CashView {

    Staging staging;

    public SimpleStringProperty missingGroups = new SimpleStringProperty() ;

    private ObservableList<Reservation> reservations = FXCollections.observableArrayList();;

    public CashView(Staging staging) {
        this.staging = staging;
        reservations.addAll(staging.reservations);
        missingGroups.setValue("5");
    }

    public Node createView() {
        VBox vBox = new VBox();

        vBox.setSpacing(Preferences.get().spacing());
        vBox.setPadding(Preferences.get().insets());

        vBox.getChildren().add(stagingTime());

        HBox table = tableAndButtons();
        table.setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(table, Priority.ALWAYS);

        vBox.getChildren().add(table);

        vBox.getChildren().add(newReservationInput());

        return vBox;
    }

    private Node stagingTime() {
        HBox hBoxLabels = new HBox();
        hBoxLabels.setSpacing(Preferences.get().spacing());
        hBoxLabels.setPadding(Preferences.get().insets());
        HBox hBoxLabelsLeft = new HBox();
        Label labelStagingTime = new Label("Vorstellung: " + DateTimeFormatter.ofPattern("HH:mm").format(staging.date));
        labelStagingTime.setFont(new Font("Arial", 24));

        hBoxLabelsLeft.getChildren().add(labelStagingTime);
        Label remaingTime = new Label(getRemainingTime() );
        remaingTime.setFont(new Font("Arial", 24));

        System.out.println("new Timer");
        new Timer().schedule(
                new TimerTask() {

                    @Override
                    public void run() {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                remaingTime.setText(getRemainingTime());
                            }
                        });


                    }
                }, 0, 5000);

        hBoxLabelsLeft.getChildren().add(remaingTime);

        hBoxLabels.getChildren().add(hBoxLabelsLeft);
        return hBoxLabels;
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
        Label labelMissingGroups = new Label("Fehlende Gäste-Gruppen: ");
        labelMissingGroups.setFont(Preferences.get().fontBig());

        hBoxLabelsLeft.getChildren().add(labelMissingGroups);
        Label missingGroupsValue = new Label("-");
        missingGroupsValue.textProperty().bindBidirectional(missingGroups);
        missingGroupsValue.setFont(Preferences.get().fontBig());

        hBoxLabelsLeft.getChildren().add(missingGroupsValue);
        hBoxLabelsLeft.setAlignment(Pos.CENTER_LEFT);

        hBoxLabels.getChildren().add(hBoxLabelsLeft);
        return hBoxLabels;
    }

    private HBox totals() {
        HBox hBoxLabels = new HBox();
        hBoxLabels.setSpacing(Preferences.get().spacing());
        hBoxLabels.setPadding(Preferences.get().insets());
        HBox hBoxLabelsLeft = new HBox();
        Label labelGroupsArrived = new Label("Gästegruppen:");
        labelGroupsArrived.setFont(Preferences.get().fontBig());

        hBoxLabelsLeft.getChildren().add(labelGroupsArrived);
        hBoxLabelsLeft.getChildren().add(new Label("-"));
        hBoxLabelsLeft.setAlignment(Pos.CENTER_LEFT);

        hBoxLabels.getChildren().add(hBoxLabelsLeft);
        HBox hBoxLabelsRight = new HBox();

        Label labelSum = new Label("Summe:");
        labelSum.setFont(Preferences.get().fontBig());

        hBoxLabelsRight.getChildren().add(labelSum);
        Label labelSumValue = new Label("235.50€");
        labelSumValue.setFont(Preferences.get().fontBig());
        hBoxLabelsRight.getChildren().add(labelSumValue);
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

        TableColumn<Reservation, String> sum = new TableColumn("Summe");
        sum.setCellValueFactory(new PropertyValueFactory<>("sum"));

        TableColumn<Reservation, Boolean> paid = new TableColumn("Bezahlt");
        //paid.setCellValueFactory(new PropertyValueFactory<>("paid"));

        paid.setCellFactory(column -> new CheckBoxTableCell<>());

        paid.setCellValueFactory(cellData -> {
                Reservation cellValue = cellData.getValue();
                BooleanProperty property = cellValue.paid;

                // Add listener to handler change
                property.addListener((observable, oldValue, newValue) -> cellValue.setPaid(newValue));

                return property;
            });



        tableView.getColumns().addAll(reservation, table, seats, sum, paid);

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


    class BooleanCell extends TableCell<Reservation, Boolean> {
        private CheckBox checkBox;
        public BooleanCell() {
            checkBox = new CheckBox();
            checkBox.setDisable(true);
            checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(isEditing())
                        commitEdit(newValue == null ? false : newValue);
                }
            });
            this.setGraphic(checkBox);
            this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            this.setEditable(true);
        }
        @Override
        public void startEdit() {
            super.startEdit();
            if (isEmpty()) {
                return;
            }
            checkBox.setDisable(false);
            checkBox.requestFocus();
        }
        @Override
        public void cancelEdit() {
            super.cancelEdit();
            checkBox.setDisable(true);
        }
        public void commitEdit(Boolean value) {
            super.commitEdit(value);
            checkBox.setDisable(true);
        }
        @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!isEmpty()) {
                checkBox.setSelected(item);
            }
        }
    }

    public String getRemainingTime() {

        LocalDateTime now = LocalDateTime.now();
        long days = ChronoUnit.DAYS.between(now,staging.date);
        if (days>1) {
            return " ("+days+" Tage)";
        }
        long hours = ChronoUnit.HOURS.between(now,staging.date);
        if (hours>2) {
            return " ("+hours+" Stunden)";
        }
        long minutes = ChronoUnit.MINUTES.between(now,staging.date);
        if (minutes>0) {
            return "("+minutes+" Minuten)";
        }

        return " (läuft)";
    }
}
