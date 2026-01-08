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

public class ShopController {

    @FXML
    private TilePane gameCardsPane;

    @FXML
    private Button backButton;

    @FXML
    private TextField searchField;

    private List<GameCard> allGameCards = new ArrayList<>();

    @FXML
    public void initialize() {
        

        int[] appIds = {730, 570, 1808500, 3419430, 431960, 252490, 230410, 2694490, 3240220, 1172470, 2507950, 413150, 271590, 2767030, 3564740, 1203220, 440, 236390, 2807960, 1086940, 1623730, 2246340, 438100, 322170, 2622380, 2923300, 322330, 1366800, 646570, 359550, 552990, 1091500, 218620, 289070, 394360, 1174180, 105600, 1938090, 1245620, 1142710, 108600, 261550, 3405690, 221100, 1903340, 2399830, 2357570, 553850, 1771300, 294100, 1326470, 251570, 1085660, 381210, 3932890, 1973530, 3551340, 3241660, 1222670};

        for (int appId : appIds) {
            GameInfo info = SteamAPI.getGameInfo(appId);
            if (info != null) {
                GameCard card = new GameCard(info, appId, true);
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
