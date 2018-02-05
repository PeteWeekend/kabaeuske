package pete.weekend.kabaeuske.view;

import javafx.scene.Node;
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

    public TabPane createView() {

        Map<String, StagingView> views = new HashMap();
        model.stagings.forEach(staging -> {
            views.put(staging.name, new StagingView(staging));
        });


        TabPane tabs = new TabPane();
        tabs.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
            //ControlView.getInstance().setCurrentStaging( views.get(nv.getText()).staging);
            views.get(nv.getText()).staging.clearPossibleReservations();
        });

        views.values().forEach(
                stagingView -> {
                    Tab tab = new Tab(stagingView.staging.name, stagingView.createView());
                    tabs.getTabs().add(tab);
                }
        );
        return tabs;
    }
}
