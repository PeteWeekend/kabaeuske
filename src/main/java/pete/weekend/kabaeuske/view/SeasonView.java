package pete.weekend.kabaeuske.view;

import pete.weekend.kabaeuske.model.Season;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.HashMap;
import java.util.Map;

public class SeasonView {

    Season model;

    public SeasonView(Season model) {
        this.model = model;
    }

    public TabPane getPane() {

        Map<String, StagingView> views = new HashMap();
        model.stagings.forEach(s -> {
            views.put(s.name, new StagingView(s));
        });


        TabPane tabs = new TabPane();
        tabs.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
            ControlView.getInstance().setCurrentStaging( views.get(nv.getText()).staging);
            views.get(nv.getText()).staging.clearPossibleReservations();
        });

        views.values().forEach(
                s -> {
                    Tab tab = new Tab(s.staging.name, s.getNode());
                    tabs.getTabs().add(tab);
                }
        );
        return tabs;
    }
}
