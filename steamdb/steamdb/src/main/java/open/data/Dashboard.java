package open.data;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class Dashboard {

    private BorderPane root;

    public Dashboard() {
        root = new BorderPane();
        createLayout();
    }

    private void createLayout() {

        Label header = new Label("Merkatuaren Laburpena");
        header.setStyle("-fx-font-size: 32px; -fx-font-weight: bold;");

        root.setTop(header);

        HBox content = new HBox(30);

        VBox leftColumn = new VBox(20);
        VBox rightColumn = new VBox(20);

        content.getChildren().addAll(leftColumn, rightColumn);

        root.setCenter(content);
    }

    public BorderPane getView() {
        return root;
    }
}

