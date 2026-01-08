package open.data;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import open.components.GameCard;
import open.model.GameInfo;

import java.io.IOException;

public class ShopController {

    @FXML
    private TilePane gameCardsPane;  // Para las cartas
    @FXML
    private Button backButton;       // Botón Atrás

    @FXML
    public void initialize() {
        // Cargar juegos en el TilePane (mismos IDs que en Dashboard)
        int[] appIds = {730, 570, 1808500, 3419430, 431960, 252490, 230410, 2694490, 3240220, 1172470, 2507950, 413150, 271590, 2767030, 3564740, 1203220, 440, 236390, 2807960, 1086940, 1623730, 2246340, 438100, 322170, 2622380, 2923300, 322330, 1366800, 646570, 359550, 552990, 1091500, 218620, 289070, 394360, 1174180, 105600, 1938090, 1245620, 1142710, 108600, 261550, 3405690, 221100, 1903340, 2399830, 2357570, 553850, 1771300, 294100, 1326470, 251570, 1085660, 381210, 3932890, 1973530, 3551340, 3241660, 1222670};

        for (int appId : appIds) {
            GameInfo info = SteamAPI.getGameInfo(appId);
            if (info != null) {
                GameCard card = new GameCard(info, appId, true); // true indica que es para Shop (mostrar precio)
                card.setPrefWidth(220);
                card.setPrefHeight(250);
                gameCardsPane.getChildren().add(card);
            }
        }

        // Configurar acción del botón Atrás
        backButton.setOnAction(e -> volverAlMain());
    }

    private void volverAlMain() {
        try {
            // Cargar el FXML del Main
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SteamDBP.fxml"));
            Parent root = loader.load();

            // Obtener el Stage actual desde el botón
            Stage stage = (Stage) backButton.getScene().getWindow();

            // Crear la nueva escena con tamaño 1000x600
            Scene scene = new Scene(root, 1000, 600);

            // Establecer la escena y mostrar
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

