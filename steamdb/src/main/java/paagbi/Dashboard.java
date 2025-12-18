package paagbi;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import paagbi.components.GameCard;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

public class Dashboard {

    private BorderPane root;
    private VBox leftColumn;
    private VBox rightColumn;

    public Dashboard() {
        root = new BorderPane();
        createLayout();
    }

    private void createLayout() {

        // Header
        Label header = new Label("Merkatuaren Laburpena");
        header.setStyle("-fx-font-size: 32px; -fx-font-weight: bold;");
        root.setTop(header);
        BorderPane.setMargin(header, new javafx.geometry.Insets(10));

        // Contenedor central
        HBox content = new HBox(30);
        content.setPadding(new javafx.geometry.Insets(10));

        leftColumn = new VBox(20);
        rightColumn = new VBox(20);

        content.getChildren().addAll(leftColumn, rightColumn);
        root.setCenter(content);

        // Sección "Orain Gehien Jokatzen direnak"
        Label topLabel = new Label("Orain Gehien Jokatzen direnak");
        topLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        leftColumn.getChildren().add(topLabel);

        ScrollPane scrollPane = new ScrollPane();
        VBox cardsContainer = new VBox(15);
        scrollPane.setContent(cardsContainer);
        scrollPane.setFitToWidth(true);
        leftColumn.getChildren().add(scrollPane);

        // Lista de AppIDs
        int[] popularGames = {730, 570, 578080, 271590, 1172470, 397540, 1091500};

        for (int appId : popularGames) {
            GameInfo info = SteamAPI.getGameInfo(appId);
            int players = SteamAPI.getCurrentPlayers(appId);
            if(info != null && players >= 0) {
                GameCard card = new GameCard(info, players);
                cardsContainer.getChildren().add(card);
            }
        }

        // Placeholder para la columna derecha
        Label placeholder = new Label("Columna derecha (Top salmentak, Generoaren banaketa...)");
        rightColumn.getChildren().add(placeholder);
    }

    public BorderPane getView() {
        return root;
    }
}
