package open.data;

import javafx.fxml.FXML;
import javafx.scene.layout.TilePane;   // <- IMPORT CORRECTO
import open.components.GameCard;
import open.model.GameInfo;

public class DashboardController {

    @FXML
    private TilePane gameCardsPane;  // Usaremos solo el TilePane, ya no necesitamos leftColumn ni rightColumn

    @FXML
    public void initialize() {
        int[] appIds = {730, 570, 1808500, 3419430, 431960, 252490, 230410, 2694490, 3240220, 1172470, 2507950, 413150, 271590, 2767030, 3564740, 1203220, 440, 236390, 2807960, 1086940, 1623730, 2246340, 438100, 322170, 2622380, 2923300, 322330, 1366800, 646570, 359550, 552990, 1091500, 218620, 289070, 394360, 1174180, 105600, 1938090, 1245620, 1142710, 108600, 261550, 3405690, 221100, 1903340, 2399830, 2357570, 553850, 1771300, 294100, 1326470, 251570, 1085660, 381210, 3932890, 1973530, 3551340, 3241660, 1222670};
//105600

        for (int appId : appIds) {
            GameInfo info = SteamAPI.getGameInfo(appId);
            if (info != null) {
                GameCard card = new GameCard(info, appId);
                card.setPrefWidth(220);   // tamaño fijo
                card.setPrefHeight(250);
                gameCardsPane.getChildren().add(card);
            }
        }
    }
}
