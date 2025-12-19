package open.components;

import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import open.data.SteamAPI;
import open.model.GameInfo;

public class GameCard extends VBox {

    public GameCard(GameInfo info, int appId) { // ya no necesitamos maxPlayers
        setSpacing(10);
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: #ffffff; " +
                 "-fx-padding: 15; " +
                 "-fx-border-radius: 15; " +
                 "-fx-background-radius: 15; " +
                 "-fx-border-color: #dddddd; " +
                 "-fx-border-width: 1px;");

        DropShadow shadow = new DropShadow();
        shadow.setRadius(5);
        shadow.setColor(Color.rgb(200, 200, 200, 0.5));
        setEffect(shadow);

        ImageView imageView = new ImageView(new Image(info.imageUrl, 200, 120, true, true));
        imageView.setStyle("-fx-border-radius: 10;");

        Label nameLabel = new Label(info.name);
        nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-alignment: center;");

        Label genreLabel = new Label(info.genre);
        genreLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #666666;");

        int players = SteamAPI.getCurrentPlayers(appId);
        Label playersLabel = new Label("Jokalari linean: " + players);
        playersLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #009688; -fx-font-weight: bold;");

        getChildren().addAll(imageView, nameLabel, genreLabel, playersLabel);
    }
}
