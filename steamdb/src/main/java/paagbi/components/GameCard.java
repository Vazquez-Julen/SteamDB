package paagbi.components;

import paagbi.GameInfo;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class GameCard extends VBox {

    public GameCard(GameInfo info, int playerCount) {

        this.setPadding(new Insets(10));
        this.setSpacing(10);
        this.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10; -fx-effect: dropshadow(two-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 2);");

        ImageView imageView = new ImageView();
        try {
            Image img = new Image(info.imageUrl, 150, 80, true, true);
            imageView.setImage(img);
        } catch (Exception e) {
            System.out.println("Errorea irudia kargatzen: " + e.getMessage());
        }

        Label nameLabel = new Label(info.name);
        nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label genreLabel = new Label("Genero: " + info.genre);
        genreLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #555555;");

        double playersInMillions = playerCount / 1_000_000.0;
        Label playersLabel = new Label(String.format("%.1fM jokalari linean", playersInMillions));
        playersLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #333333;");

        ProgressBar progressBar = new ProgressBar(Math.min(1.0, playersInMillions / 5));
        progressBar.setPrefWidth(150);

        this.getChildren().addAll(imageView, nameLabel, genreLabel, playersLabel, progressBar);
        this.setAlignment(Pos.CENTER);
    }
}
