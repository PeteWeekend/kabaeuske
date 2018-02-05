package pete.weekend.kabaeuske.view;

import pete.weekend.kabaeuske.model.Staging;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ControlView {

    Staging staging;

    public ControlView(Staging staging) {
        this.staging = staging;

    }

    public Node createView() {

        ReservationView reservationView = new ReservationView(this.staging);
        CashView cashView = new CashView(this.staging);

        TabPane tabs = new TabPane();
        Tab reservierung = new Tab("Reservierung", reservationView.createView());
        tabs.getTabs().add(reservierung);
        Tab kasse = new Tab("Kasse", cashView.createView());
        tabs.getTabs().add(kasse);


        return tabs;

    }

}
