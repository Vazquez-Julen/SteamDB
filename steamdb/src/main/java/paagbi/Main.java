package paagbi;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        Dashboard dashboard = new Dashboard();
        Scene scene = new Scene(dashboard.getView(), 1200, 800);

        stage.setTitle("Merkatuaren Laburpena");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
