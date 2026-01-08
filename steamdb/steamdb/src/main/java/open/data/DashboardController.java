package open.data;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import open.components.GameCard;
import open.model.GameInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DashboardController {

    @FXML
    private TilePane gameCardsPane;

    @FXML
    private Button backButton;

    @FXML
    private TextField searchField;

    private List<GameCard> allGameCards = new ArrayList<>();

    @FXML
    public void initialize() {

        int[] appIds = {730, 570, 1808500, 3419430, 431960, 252490, 230410};

        for (int appId : appIds) {
            GameInfo info = SteamAPI.getGameInfo(appId);
            if (info != null) {
                GameCard card = new GameCard(info, appId);
                card.setPrefWidth(220);
                card.setPrefHeight(250);

                allGameCards.add(card);
                gameCardsPane.getChildren().add(card);
            }
        }

        searchField.textProperty().addListener((obs, oldText, newText) ->
                filtrarJuegos(newText)
        );

        backButton.setOnAction(e -> volverAlMain());
    }

    private void filtrarJuegos(String texto) {
        gameCardsPane.getChildren().clear();

        if (texto == null || texto.isEmpty()) {
            gameCardsPane.getChildren().addAll(allGameCards);
            return;
        }

        String filtro = texto.toLowerCase();

        for (GameCard card : allGameCards) {
            if (card.getGameInfo().name.toLowerCase().contains(filtro)) {
                gameCardsPane.getChildren().add(card);
            }
        }
    }

    private void volverAlMain() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SteamDBP.fxml"));
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
