package pete.weekend.kabaeuske.view;

import pete.weekend.kabaeuske.model.Staging;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.*;

public class StagingView {


    Staging staging;

    public StagingView(Staging staging) {
        this.staging =staging;
    }

    public Node getNode() {

        HBox hbox = new HBox();

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5,5,5,5));

        gridPane.setHgap(10f);gridPane.setVgap(10f);
        staging.seating.tables.forEach(
                table -> {
                    gridPane.add(new TableView(table).getNode(),table.col,table.row);
                }
        );

        hbox.getChildren().add(gridPane);
        Node control = ControlView.getInstance().getNode();
        hbox.getChildren().add(control);

        HBox.setHgrow(gridPane, Priority.ALWAYS);
        HBox.setHgrow(control, Priority.ALWAYS);
        return hbox;
    }


}
