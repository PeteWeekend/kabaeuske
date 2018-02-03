package pete.weekend.kabaeuske.view;

import pete.weekend.kabaeuske.model.Table;
import javafx.scene.Node;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class TableView {

    Table table;
    public TableView(Table table){
        this.table=table;
    }

    public Node getNode() {

        VBox tableNode = new VBox();
        tableNode.setSpacing(2);
        tableNode.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        GridPane tableGrid = new GridPane();
        Text text = new Text(table.name);
        text.setTextAlignment(TextAlignment.CENTER);
        tableGrid.add(text,1,1);
        tableGrid.setHgap(1);
        tableGrid.setVgap(1);

        table.seats.forEach(c->{
            tableGrid.add(new SeatView(c).getView(),c.col,c.row);
        });
        tableNode.getChildren().add(tableGrid);
        return tableNode;
    }

}
