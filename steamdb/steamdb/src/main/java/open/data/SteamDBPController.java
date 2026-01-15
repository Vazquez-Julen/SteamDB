package open.data;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * SteamDBPController klaseak aplikazioaren menu nagusia kontrolatzen du.
 * Bertatik "Players" eta "Shop" pantailara nabigatzeko aukera ematen du.
 */
public class SteamDBPController {

    // "Players" pantailara joateko botoia
    @FXML
    private Button players;

    // "Shop" pantailara joateko botoia
    @FXML
    private Button shop;

    /**
     * FXML fitxategia kargatu ondoren exekutatzen den metodoa.
     * Botoien ekintzak eta efektu bisualak definitzen ditu.
     */
    @FXML
    public void initialize() {

        // "Players" botoia sakatzean Dashboard pantaila kargatzen da
        players.setOnAction(e -> {
            try {
                Parent root = FXMLLoader.load(
                        getClass().getResource("/open/data/Dashboard.fxml")
                );

                // Uneko leihoa lortzen da
                Stage stage = (Stage) players.getScene().getWindow();

                // Eszena berria sortu eta ezartzen da
                Scene scene = new Scene(root, 1200, 800);
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                // Errorea kontsolan bistaratzen da
                ex.printStackTrace();
            }
        });

        // "Shop" botoia sakatzean Shop pantaila kargatzen da
        shop.setOnAction(e -> {
            try {
                Parent root = FXMLLoader.load(
                        getClass().getResource("/open/data/Shop.fxml")
                );

                // Uneko leihoa lortzen da
                Stage stage = (Stage) shop.getScene().getWindow();

                // Eszena berria sortu eta ezartzen da
                Scene scene = new Scene(root, 1200, 800);
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                // Errorea kontsolan bistaratzen da
                ex.printStackTrace();
            }
        });

        // "Players" botoiarentzako hover efektua (sagua gainean dagoenean)
        players.setOnMouseEntered(e -> players.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #3a7bd5, #1b3a5a); " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 16px; " +
                "-fx-font-weight: bold; " +
                "-fx-background-radius: 8px; " +
                "-fx-border-radius: 8px; " +
                "-fx-border-color: #3a7bd5; " +
                "-fx-border-width: 2px;"
        ));

        // Sagua ateratzean jatorrizko estiloa berrezartzen da
        players.setOnMouseExited(e -> players.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #1b3a5a, #0f2a44); " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 16px; " +
                "-fx-font-weight: bold; " +
                "-fx-background-radius: 8px; " +
                "-fx-border-radius: 8px; " +
                "-fx-border-color: #3a7bd5; " +
                "-fx-border-width: 2px;"
        ));

        // "Shop" botoiarentzako hover efektua
        shop.setOnMouseEntered(e -> shop.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #3a7bd5, #1b3a5a); " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 16px; " +
                "-fx-font-weight: bold; " +
                "-fx-background-radius: 8px; " +
                "-fx-border-radius: 8px; " +
                "-fx-border-color: #3a7bd5; " +
                "-fx-border-width: 2px;"
        ));

        // Sagua ateratzean estilo lehenetsia berrezartzen da
        shop.setOnMouseExited(e -> shop.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #1b3a5a, #0f2a44); " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 16px; " +
                "-fx-font-weight: bold; " +
                "-fx-background-radius: 8px; " +
                "-fx-border-radius: 8px; " +
                "-fx-border-color: #3a7bd5; " +
                "-fx-border-width: 2px;"
        ));
    }
}
