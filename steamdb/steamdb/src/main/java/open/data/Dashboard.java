package open.data;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

/**
 * Dashboard klaseak aplikazioaren panel nagusia irudikatzen du.
 * JavaFX erabiliz egitura bisual nagusi bat sortzen du.
 */
public class Dashboard {

    // Interfazearen erro nagusia: BorderPane
    private BorderPane root;

    /**
     * Eraikitzailea (constructor).
     * BorderPane-a hasieratzen du eta layout-a sortzen du.
     */
    public Dashboard() {
        root = new BorderPane();
        createLayout();
    }

    /**
     * Interfazearen egitura sortzen duen metodoa.
     * Goiburua eta erdiko edukia definitzen ditu.
     */
    private void createLayout() {

        // Goiko titulua sortzen da
        Label header = new Label("Merkatuaren Laburpena");
        header.setStyle("-fx-font-size: 32px; -fx-font-weight: bold;");

        // Goiburua BorderPane-aren goiko aldean kokatzen da
        root.setTop(header);

        // Eduki nagusia antolatzeko HBox bat sortzen da (zutabe horizontalak)
        HBox content = new HBox(30); // 30px-ko tartea zutabeen artean

        // Ezkerreko eta eskuineko zutabeak sortzen dira
        VBox leftColumn = new VBox(20);  // 20px-ko tartea elementuen artean
        VBox rightColumn = new VBox(20);

        // Bi zutabeak HBox barruan gehitzen dira
        content.getChildren().addAll(leftColumn, rightColumn);

        // Edukia BorderPane-aren erdian kokatzen da
        root.setCenter(content);
    }

    /**
     * Dashboard-aren ikuspegia itzultzen duen metodoa.
     * Beste klase batzuek interfazea erabiltzeko baliagarria.
     */
    public BorderPane getView() {
        return root;
    }
}
