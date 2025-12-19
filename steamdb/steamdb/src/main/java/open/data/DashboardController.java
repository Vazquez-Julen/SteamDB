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
        int[] appIds = {730,570,578080,1808500,1091500,211820,252490,1085660,1245620,232090,427520,322330,105600,24010,817100,42360,493340,1172470,440,12210,297940,205060,977480,236850,1151340,1217020,386360,941990,196490,1172470,1245620,8190,7550,238960,594570,218620,588650,493340,24010,1131930,328530,834630,431960,1145360,445220,511780,978820,489830,238610,1465360,1057090,231430,622970,232090,275850,252490,225540,235360,912740,1057090,358720,304930,39210,239140,527230,1182470,1174180,642970,1551360,483940,816730,1172470,222880};


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
