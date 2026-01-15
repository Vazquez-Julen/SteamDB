package open.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import open.model.GameInfo;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * SteamAPI klaseak Steam-eko API desberdinekin komunikazioa kudeatzen du.
 * Jokoen informazioa, unean uneko jokalari kopurua eta prezioak lortzeko erabiltzen da.
 */
public class SteamAPI {

    // Uneko jokalari kopurua lortzeko APIaren URLa
    private static final String PLAYER_COUNT_URL =
            "https://api.steampowered.com/ISteamUserStats/GetNumberOfCurrentPlayers/v1/?appid=";

    // Steam Store-ko jokoen informazioa lortzeko APIaren URLa
    private static final String STORE_URL =
            "https://store.steampowered.com/api/appdetails?appids=";

    /**
     * Joko baten unean uneko jokalari kopurua itzultzen du.
     *
     * @param appId Steam-eko jokoaren identifikatzailea
     * @return jokalari kopurua edo -1 errorea gertatzen bada
     */
    public static int getCurrentPlayers(int appId) {
        try {
            // APIari deia egiteko URLa sortzen da
            URL url = new URL(PLAYER_COUNT_URL + appId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Erantzuna irakurri eta JSONera bihurtzen da
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            JsonObject json = JsonParser.parseReader(reader)
                    .getAsJsonObject()
                    .getAsJsonObject("response");

            // Jokalari kopurua itzultzen da
            return json.get("player_count").getAsInt();

        } catch (Exception e) {
            // Errorea gertatuz gero, mezua erakutsi eta -1 itzultzen da
            System.out.println("Errorea Steam API deitzen: " + e.getMessage());
            return -1;
        }
    }

    /**
     * Joko baten oinarrizko informazioa lortzen du (izena, irudia eta generoa).
     *
     * @param appId Steam-eko jokoaren identifikatzailea
     * @return GameInfo objektua edo null errorea gertatuz gero
     */
    public static GameInfo getGameInfo(int appId) {
        try {
            // Steam Store APIari deia egiteko konexioa sortzen da
            URL url = new URL(STORE_URL + appId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // JSON erantzuna irakurtzen da
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            JsonObject json = JsonParser.parseReader(reader)
                    .getAsJsonObject()
                    .getAsJsonObject(String.valueOf(appId))
                    .getAsJsonObject("data");

            // Jokoaren izena eta goiburuko irudia lortzen dira
            String name = json.get("name").getAsString();
            String image = json.get("header_image").getAsString();

            // Genero lehenetsia
            String genre = "Besteak";
            if (json.has("genres")) {
                JsonArray genres = json.getAsJsonArray("genres");
                if (genres.size() > 0) {
                    // Jatorrizko generoaren izena lortzen da
                    String original = genres.get(0)
                            .getAsJsonObject()
                            .get("description")
                            .getAsString();

                    // Generoak euskarara egokitzen dira
                    switch (original) {
                        case "Action": genre = "Ekintza"; break;
                        case "Strategy": genre = "Estrategia"; break;
                        case "RPG": genre = "RPG"; break;
                        default: genre = "Besteak";
                    }
                }
            }

            // GameInfo objektua sortu eta itzultzen da
            return new GameInfo(name, image, genre);

        } catch (Exception e) {
            // Errorea gertatuz gero, mezua erakutsi eta null itzultzen da
            System.out.println("Errorea Steam Store API: " + e.getMessage());
            return null;
        }
    }

    /**
     * Joko baten prezioa lortzen du eurotan.
     *
     * @param appId Steam-eko jokoaren identifikatzailea
     * @return prezioa testu gisa (adib. "19.99 €", "Doan", edo "Ez dago eskuragarri")
     */
    public static String getGamePrice(int appId) {
        try {
            // cc=ES parametroa gehitzen da prezioak eurotan behartzeko
            URL url = new URL(STORE_URL + appId + "&cc=ES");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // JSON erantzuna irakurtzen da
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            JsonObject json = JsonParser.parseReader(reader)
                    .getAsJsonObject()
                    .getAsJsonObject(String.valueOf(appId));

            // APIak arrakastarik izan ez badu
            if (!json.get("success").getAsBoolean()) {
                return "Ez dago eskuragarri";
            }

            JsonObject data = json.getAsJsonObject("data");

            // Jokoa doakoa den egiaztatzen da
            if (data.has("is_free") && data.get("is_free").getAsBoolean()) {
                return "Doan";
            }

            // Prezioaren informazioa badago
            if (data.has("price_overview")) {
                JsonObject priceOverview = data.getAsJsonObject("price_overview");
                int finalPrice = priceOverview.get("final").getAsInt();

                // Zentimoak eurotara bihurtzen dira
                double price = finalPrice / 100.0;

                // Prezioa eurotan formateatzen da
                return String.format("%.2f €", price);
            }

            return "Ez dago eskuragarri";

        } catch (Exception e) {
            // Errorea gertatuz gero
            System.out.println("Errorea prezioak lortzean: " + e.getMessage());
            return "Ez dago eskuragarri";
        }
    }
}
