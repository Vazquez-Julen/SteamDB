package open.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import open.data.SteamAPI;
import open.model.GameInfo;

public class GameCard extends VBox {

    private final GameInfo info;
    private final int appId;
    private final boolean isShop;

    // Constructor para Players
    public GameCard(GameInfo info, int appId) {
        this(info, appId, false);
    }

    // Constructor para Shop
    public GameCard(GameInfo info, int appId, boolean isShop) {
        this.info = info;
        this.appId = appId;
        this.isShop = isShop;
        crearCarta();
    }

    private void crearCarta() {

        setSpacing(10);
        setPadding(new Insets(10));
        setAlignment(Pos.TOP_CENTER);
        setStyle(
            "-fx-background-color: #0f2a44;" +
            "-fx-background-radius: 10px;" +
            "-fx-border-radius: 10px;" +
            "-fx-border-color: #3a7bd5;" +
            "-fx-border-width: 2px;"
        );

        // Imagen
        ImageView imageView = new ImageView();
        imageView.setFitWidth(200);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);

        try {
            imageView.setImage(new Image(info.imageUrl, true));
        } catch (Exception ignored) {
        }

        // Nombre
        Label nameLabel = new Label(info.name);
        nameLabel.setWrapText(true);
        nameLabel.setAlignment(Pos.CENTER);
        nameLabel.setStyle(
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;"
        );

        // Género
        Label genreLabel = new Label(info.genre);
        genreLabel.setStyle(
            "-fx-text-fill: #a9c7e8;" +
            "-fx-font-size: 12px;"
        );

        getChildren().addAll(imageView, nameLabel, genreLabel);

        // Players
        if (!isShop) {
            int players = SteamAPI.getCurrentPlayers(appId);
            Label playersLabel = new Label(
                    players >= 0 ? players + " jokalari" : "Daturik ez"
            );
            playersLabel.setStyle(
                "-fx-text-fill: #4fc3f7;" +
                "-fx-font-size: 12px;"
            );
            getChildren().add(playersLabel);
        }

        // Shop
        if (isShop) {
            String price = SteamAPI.getGamePrice(appId);
            Label priceLabel = new Label(price);
            priceLabel.setStyle(
                "-fx-text-fill: #81c784;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;"
            );
            getChildren().add(priceLabel);
        }
    }

    // 🔑 Necesario para la búsqueda
    public GameInfo getGameInfo() {
        return info;
    }
}
