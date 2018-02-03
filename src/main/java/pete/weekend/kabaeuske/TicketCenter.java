package pete.weekend.kabaeuske;

import pete.weekend.kabaeuske.model.Season;
import pete.weekend.kabaeuske.view.ControlView;
import pete.weekend.kabaeuske.view.SeasonView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TicketCenter extends Application {

    Season season = DummyData.createDummy();

    @Override
    public void start(Stage primaryStage) throws Exception {


        ControlView.getInstance().setSeason(season);
        ControlView.getInstance().setCurrentStaging(season.stagings.get(0));

        primaryStage.setTitle("Kabäuske Ticket Center - " + season.name);
        primaryStage.setScene(new Scene(new SeasonView(season).getPane(), 1024, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
