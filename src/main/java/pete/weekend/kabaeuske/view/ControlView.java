package pete.weekend.kabaeuske.view;

import pete.weekend.kabaeuske.model.Season;
import pete.weekend.kabaeuske.model.Staging;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ControlView {

    Season season;
    ReservationView reservationView = new ReservationView();
    CashView cashView = new CashView();

    Staging currentStaging;

    static ControlView instance = new ControlView();

    public static ControlView getInstance() {
        return instance;
    }
    private ControlView() {
    }

    public void setCurrentStaging(Staging currentStaging) {
        this.currentStaging = currentStaging;
        reservationView.setStaging(currentStaging);
        cashView.setStaging(currentStaging);
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Node getNode() {

        TabPane tabs = new TabPane();
        Tab reservierung = new Tab("Reservierung", reservationView.getNode());
        tabs.getTabs().add(reservierung);
        Tab kasse = new Tab("Kasse", cashView.getNode());
        tabs.getTabs().add(kasse);


        return tabs;

    }

}
