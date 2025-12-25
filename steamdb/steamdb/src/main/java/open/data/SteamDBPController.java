package open.data;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SteamDBPController {

    @FXML
    private Button players;

    @FXML
    private Button shop;

    @FXML
    public void initialize() {
        // Acción al pulsar el botón Players
        players.setOnAction(e -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/open/data/Dashboard.fxml"));
                Stage stage = (Stage) players.getScene().getWindow();
                Scene scene = new Scene(root, 1200, 800);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // Acción al pulsar el botón Shop
        shop.setOnAction(e -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/open/data/Shop.fxml"));
                Stage stage = (Stage) shop.getScene().getWindow();
                Scene scene = new Scene(root, 1200, 800);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // Efecto hover para el botón Players
        players.setOnMouseEntered(e -> players.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #3a7bd5, #1b3a5a); -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 8px; -fx-border-radius: 8px; -fx-border-color: #3a7bd5; -fx-border-width: 2px;"
        ));
        players.setOnMouseExited(e -> players.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #1b3a5a, #0f2a44); -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 8px; -fx-border-radius: 8px; -fx-border-color: #3a7bd5; -fx-border-width: 2px;"
        ));

        // Efecto hover para el botón Shop
        shop.setOnMouseEntered(e -> shop.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #3a7bd5, #1b3a5a); -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 8px; -fx-border-radius: 8px; -fx-border-color: #3a7bd5; -fx-border-width: 2px;"
        ));
        shop.setOnMouseExited(e -> shop.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #1b3a5a, #0f2a44); -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 8px; -fx-border-radius: 8px; -fx-border-color: #3a7bd5; -fx-border-width: 2px;"
        ));
    }
}
