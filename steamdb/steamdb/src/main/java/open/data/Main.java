package open.data;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Main klasea aplikazioaren sarrera-puntua da.
 * JavaFX aplikazioa abiarazteaz eta leiho nagusia kargatzeaz arduratzen da.
 */
public class Main extends Application {

    /**
     * JavaFX-k automatikoki deitzen duen start metodoa.
     * Leiho nagusia (Stage) konfiguratu eta bistaratzen du.
     */
    @Override
    public void start(Stage stage) throws Exception {

        // FXML fitxategia kargatzeko FXMLLoader objektua sortzen da
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/open/data/SteamDBP.fxml")
        );

        // Eszena sortzen da, tamaina lehenetsiarekin
        Scene scene = new Scene(loader.load(), 1000, 600);

        // Leihoaren titulua ezartzen da
        stage.setTitle("SteamDB");

        // Aplikazioaren ikonoa ezartzen da
        stage.getIcons().add(
            new Image(getClass().getResourceAsStream("/open/data/SteamDBLogo.png"))
        );

        // Leihoaren tamaina ezin da aldatu
        stage.setResizable(false);

        // Eszena Stage-ari esleitzen zaio
        stage.setScene(scene);

        // Leihoa bistaratzen da
        stage.show();
    }

    /**
     * Aplikazioaren main metodoa.
     * JavaFX aplikazioa abiarazten du.
     *
     * @param args komando-lerroko argumentuak
     */
    public static void main(String[] args) {
        launch();
    }
}
