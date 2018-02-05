package pete.weekend.kabaeuske.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pete.weekend.kabaeuske.Preferences;
import pete.weekend.kabaeuske.model.Staging;
import javafx.scene.Node;
import javafx.scene.layout.*;

public class StagingView {



    Staging staging;

    public StagingView(Staging staging) {
        this.staging =staging;
    }

    public Node createView() {

        HBox hbox = new HBox();
        Image logo = new Image("/KabaeuskeLogo.png",Preferences.get().seatWidth()*4, Preferences.get().seatHeight()*3, false, false, false);
        ImageView logoView = new ImageView(logo);
        GridPane gridPane = new GridPane();
        gridPane.setPadding(Preferences.get().insets());

        gridPane.setHgap(10f);gridPane.setVgap(10f);
        staging.seating.tables.forEach(
                table -> {
                    gridPane.add(new TableView(table).createView(),table.col,table.row);
                }
        );
        gridPane.add(logoView,3,4);

        hbox.getChildren().add(gridPane);
        Node control = new ControlView(staging).createView();
        hbox.getChildren().add(control);

        HBox.setHgrow(gridPane, Priority.ALWAYS);
        HBox.setHgrow(control, Priority.ALWAYS);
        return hbox;
    }


}
