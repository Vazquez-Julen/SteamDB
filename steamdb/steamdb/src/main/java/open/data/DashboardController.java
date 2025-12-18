package open.data;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import open.components.GameCard;
import open.model.GameInfo;

public class DashboardController {

    @FXML
    private VBox leftColumn;

    @FXML
    private VBox rightColumn;

    @FXML
    public void initialize() {
        // Ejemplo de IDs de juegos
        int[] appIds = {730, 570, 440, 578080}; // CS:GO, Dota 2, TF2, PUBG

        for (int i = 0; i < appIds.length; i++) {
            GameInfo info = SteamAPI.getGameInfo(appIds[i]);
            if (info != null) {
                GameCard card = new GameCard(info, 1000000); // ejemplo de maxPlayers
                if (i % 2 == 0) {
                    leftColumn.getChildren().add(card);
                } else {
                    rightColumn.getChildren().add(card);
                }
            }
        }
    }
}
