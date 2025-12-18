package open.components;

import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.geometry.Pos;
import open.data.SteamAPI;
import open.model.GameInfo;
public class GameCard extends VBox {

    public GameCard(GameInfo info, int maxPlayers) {
        setSpacing(10);
        setStyle("-fx-background-color: #f5f5f5; -fx-padding: 10; -fx-border-radius: 10; -fx-background-radius: 10;");
        setAlignment(Pos.CENTER);

        ImageView imageView = new ImageView(new Image(info.imageUrl, 150, 100, true, true));
        Label nameLabel = new Label(info.name + " (" + info.genre + ")");
        int players = SteamAPI.getCurrentPlayers(730); // ejemplo
        Label playersLabel = new Label("Jokalari linean: " + players);

        ProgressBar progress = new ProgressBar();
        if (maxPlayers > 0) progress.setProgress((double) players / maxPlayers);

        getChildren().addAll(imageView, nameLabel, playersLabel, progress);
    }
}
