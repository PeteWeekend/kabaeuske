package pete.weekend.kabaeuske.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import pete.weekend.kabaeuske.model.Table;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class TableView {

    Table table;
    public TableView(Table table){
        this.table=table;
    }

    public Node createView() {

        VBox tableNode = new VBox();
        tableNode.setSpacing(2);
        tableNode.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        GridPane tableGrid = new GridPane();
        StackPane tableStck = new StackPane();
        Image tablepng = new Image("/table.png",48, 48, false, false, false);
        ImageView tv = new ImageView(tablepng);
        tableStck.getChildren().add(tv);


        Text text = new Text(table.name+"\n \n ");
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFill(Color.WHITE);
        tableStck.getChildren().add(text);
        tableGrid.add(tableStck,1,1);
        tableGrid.setHgap(1);
        tableGrid.setVgap(1);

        table.seats.forEach(seat->{
            tableGrid.add(new SeatView(seat).createView(),seat.col,seat.row);
        });
        tableNode.getChildren().add(tableGrid);
        return tableNode;
    }

}
