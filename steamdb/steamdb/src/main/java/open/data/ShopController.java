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

/**
 * ShopController klaseak dendako interfazearen kontrola kudeatzen du.
 * Jokoak erakutsi, bilatu eta pantaila nagusira itzultzeko logika dauka.
 */
public class ShopController {

    // Joko-txartelak erakusten diren panela
    @FXML
    private TilePane gameCardsPane;

    // Atzera egiteko botoia
    @FXML
    private Button backButton;

    // Jokoak bilatzeko testu-eremua
    @FXML
    private TextField searchField;

    // Joko-txartel guztien zerrenda (filtrorik gabe)
    private List<GameCard> allGameCards = new ArrayList<>();

    /**
     * FXML fitxategia kargatu ondoren automatikoki exekutatzen den metodoa.
     * Jokoen zerrenda sortzen du, bilaketa-konportamendua ezartzen du
     * eta atzera botoiaren ekintza definitzen du.
     */
    @FXML
    public void initialize() {

        // Steam-eko jokoen AppID zerrenda
        int[] appIds = {
            730, 570, 1808500, 3419430, 431960, 252490, 230410, 2694490,
            3240220, 1172470, 2507950, 413150, 271590, 2767030, 3564740,
            1203220, 440, 236390, 2807960, 1086940, 1623730, 2246340,
            438100, 322170, 2622380, 2923300, 322330, 1366800, 646570,
            359550, 552990, 1091500, 218620, 289070, 394360, 1174180,
            105600, 1938090, 1245620, 1142710, 108600, 261550, 3405690,
            221100, 1903340, 2399830, 2357570, 553850, 1771300, 294100,
            1326470, 251570, 1085660, 381210, 3932890, 1973530, 3551340,
            3241660, 1222670
        };

        // AppID bakoitzerako jokoaren informazioa lortu eta txartela sortu
        for (int appId : appIds) {
            GameInfo info = SteamAPI.getGameInfo(appId);

            // Informazioa badagoenean, GameCard sortzen da (denda-moduan)
            if (info != null) {
                GameCard card = new GameCard(info, appId, true);

                // Txartelaren tamaina ezartzen da
                card.setPrefWidth(220);
                card.setPrefHeight(250);

                // Zerrendan eta panelean gehitzen da
                allGameCards.add(card);
                gameCardsPane.getChildren().add(card);
            }
        }

        // Bilaketa-eremuan idaztean filtro dinamikoa aplikatzen da
        searchField.textProperty().addListener((obs, oldText, newText) ->
                filtrarJuegos(newText)
        );

        // Atzera botoiaren ekintza definitzen da
        backButton.setOnAction(e -> volverAlMain());
    }

    /**
     * Jokoak bilaketa-testuaren arabera filtratzen dituen metodoa.
     *
     * @param texto erabiltzaileak bilaketa-eremuan idatzitako testua
     */
    private void filtrarJuegos(String texto) {
        // Uneko txartel guztiak kentzen dira paneletik
        gameCardsPane.getChildren().clear();

        // Testua hutsik badago, joko guztiak berriro erakusten dira
        if (texto == null || texto.isEmpty()) {
            gameCardsPane.getChildren().addAll(allGameCards);
            return;
        }

        // Konparaketarako testua minuskulaz bihurtzen da
        String filtro = texto.toLowerCase();

        // Joko bakoitza aztertzen da
        for (GameCard card : allGameCards) {
            if (card.getGameInfo().name.toLowerCase().contains(filtro)) {
                gameCardsPane.getChildren().add(card);
            }
        }
    }

    /**
     * Pantaila nagusira (SteamDBP.fxml) itzultzeko metodoa.
     */
    private void volverAlMain() {
        try {
            // FXML nagusia kargatzen da
            Parent root = FXMLLoader.load(getClass().getResource("SteamDBP.fxml"));

            // Uneko leihoa lortzen da
            Stage stage = (Stage) backButton.getScene().getWindow();

            // Eszena berria ezartzen da
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        } catch (IOException e) {
            // Errorea kontsolan erakusten da
            e.printStackTrace();
        }
    }
}
